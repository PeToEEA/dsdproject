package com.fhi.dsdproject

class RestAsyncPostRequest {

    Long id
    Long version

    Date dateCreated
    Date lastUpdated

    Date lastRelayAttempt = null
    Date acceptTime = null

    Node node
    String json

    static constraints = {
        lastRelayAttempt nullable: true
        acceptTime nullable: true
        json maxSize: 32768
    }

    public RestAsyncPostRequest(String jsonData, Node node) {
        this.json = jsonData
        this.node = node
    }
}
