package com.fhi.dsdproject

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import groovy.json.JsonSlurper


@Transactional
class RestAsyncPostRequestService {


    /**
     * Vytvori entitu RestAsyncPostRequest ktora obsahuje vsetko potrebne na
     * znovuodoslanie poziadavky ktora nebola predtym uspesne prijata druhou stranou.
     * **/

    public void createAsyncPostRequestService(String jsonData, Node node, String action) {
        RestAsyncPostRequest restAsyncPostRequest = new RestAsyncPostRequest(jsonData, node, action)
        restAsyncPostRequest.save(failOnError: true)
    }


    /**
     * Pokusi sa znova odoslat vsetky doteraz neprijate poziadavky, t.j. take ktore namaju zaznamenany cas prijatia.
     * **/

    public void sendRequests() {
        List<RestAsyncPostRequest> restAsyncPostRequests = RestAsyncPostRequest.findAllByAcceptTimeIsNull()
        restAsyncPostRequests.each { RestAsyncPostRequest restAsyncPostRequest ->
            makePostRequest(restAsyncPostRequest)
        }
    }

    /**
     * Posle poziadavku na node, v pripade uspesneho prijatia ulozi cas prijatia do RestAsyncPostRequest entity
     **/

    public void makePostRequest(RestAsyncPostRequest restAsyncPostRequest) {
        restAsyncPostRequest.lastRelayAttempt = new Date()
        Boolean success = makePostRequest(restAsyncPostRequest.json, restAsyncPostRequest.node, restAsyncPostRequest.action)
        if(success) {
            restAsyncPostRequest.acceptTime = new Date()
        }
        restAsyncPostRequest.save(failOnError: true)
    }

    /**
     * Vytvori post poziadavku na dany uzol
     * parametre:
     *
     * jsonData - retazec znakov obsahujuci Json s datami
     * node - uzol na ktory ideme poslat poziadavku
     * action - typ akcie (input/delete)
     * */
    public Boolean makePostRequest(String jsonData,  Node node, String action) {
        RestBuilder rest = new RestBuilder()
        log.info("Going to send request to ${node.url+action} with json data:\n${jsonData}\n")

        try {
            RestResponse resp = rest.post(node.url + action) {
                contentType 'application/json'
                json new JsonSlurper().parseText(jsonData)
            }
            log.info("Response: " + resp)
            if(resp.getStatus() == 200) {
                return true
            }
            return false
        } catch(Exception e) {
            return false
        }
    }
}
