package com.fhi.dsdproject

/**
 * ORM Entita
 * Reprezentuje zaznam o poziadavke na iny uzol ktora nemohla byt prijata.
 * Nasledne ju RestAsyncPostRequestJob skusa poziadavku tuto ulozenu znova odosielat
 * v pravidelnych intervaloch kym ju dany uzol neprijme.
 *
 **/

class RestAsyncPostRequest {

    Long id
    Long version

    Date dateCreated
    Date lastUpdated

    Date lastRelayAttempt = null
    Date acceptTime = null

    Node node
    String json
    String action

    static constraints = {
        lastRelayAttempt nullable: true
        acceptTime nullable: true
        json maxSize: 32768
    }

    public RestAsyncPostRequest(String jsonData, Node node, String action) {
        this.action = action
        this.json = jsonData
        this.node = node
        this.lastRelayAttempt = new Date()
    }
}
