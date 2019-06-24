package com.fhi.dsdproject

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import groovy.json.JsonSlurper


@Transactional
class RestAsyncPostRequestService {

    public void createAsyncPostRequestService(String jsonData, Node node, String action) {
        RestAsyncPostRequest restAsyncPostRequest = new RestAsyncPostRequest(jsonData, node, action)
        restAsyncPostRequest.save(failOnError: true)
    }

    public void sendRequests() {
        List<RestAsyncPostRequest> restAsyncPostRequests = RestAsyncPostRequest.findAllByAcceptTimeIsNull()
        restAsyncPostRequests.each { RestAsyncPostRequest restAsyncPostRequest ->
            makePostRequest(restAsyncPostRequest)
        }
    }

    public void makePostRequest(RestAsyncPostRequest restAsyncPostRequest) {
        restAsyncPostRequest.lastRelayAttempt = new Date()
        Boolean success = makePostRequest(restAsyncPostRequest.json, restAsyncPostRequest.node, restAsyncPostRequest.action)
        if(success) {
            restAsyncPostRequest.acceptTime = new Date()
        }
        restAsyncPostRequest.save(failOnError: true)
    }

    public Boolean makePostRequest(String jsonData,  Node node, String action) {
        RestBuilder rest = new RestBuilder()
        log.info("Going to send request to ${node.url+action} with json data:\n${jsonData}\n")

        try {

            RestResponse resp = rest.post(node.url) {
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
