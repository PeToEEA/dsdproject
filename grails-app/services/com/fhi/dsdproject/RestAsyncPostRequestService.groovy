package com.fhi.dsdproject

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional


@Transactional
class RestAsyncPostRequestService {

    public void createAsyncPostRequestService(String jsonData, Node node) {
        RestAsyncPostRequest restAsyncPostRequest = new RestAsyncPostRequest(jsonData, node)
        restAsyncPostRequest.save(failOnError: true)
    }

    public void sendRequests() {
        List<RestAsyncPostRequest> restAsyncPostRequests = RestAsyncPostRequest.findAllByAcceptTimeIsNull()
        restAsyncPostRequests.each { RestAsyncPostRequest restAsyncPostRequest ->
            makePostRequest(restAsyncPostRequest)
        }
    }

    public void makePostRequest(RestAsyncPostRequest restAsyncPostRequest) {
        RestBuilder rest = new RestBuilder()
        RestResponse resp = rest.post(restAsyncPostRequest.node.url){
            contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
            json restAsyncPostRequest.json
        }
        log.info("Response: " + resp)
    }
}
