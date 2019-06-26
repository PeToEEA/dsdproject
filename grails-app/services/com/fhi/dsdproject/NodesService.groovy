package com.fhi.dsdproject

import grails.transaction.Transactional

@Transactional
class NodesService {

    def restAsyncPostRequestService

    /**
     * Metóda ktorá zmaže záznam o uzle
     *
     * **/


    public Boolean delete(Long id) {
        Node node = Node.findById(id)
        if(node) {
            node.delete()
            return true
        }
        return false
    }

    /**
     * Metóda ktorá vykoná úpravu záznamu o uzle
     *
     * **/


    public Long edit(NodeEditCmd nodeEditCmd) {
        Node node = null
        if(nodeEditCmd.id) {
            node = Node.findById(nodeEditCmd.id)
            copyProps(node, nodeEditCmd)
            node.save(failOnError: true)
        } else {
            node = new Node()
            copyProps(node, nodeEditCmd)
            node.save(failOnError: true)
        }
        return node.id
    }

    private void copyProps(Node node, NodeEditCmd nodeEditCmd) {
        node.url = nodeEditCmd.url
        node.name = nodeEditCmd.name
    }

    /**
     * Metóda ktorá vyvolá zaslanie dát o tovare na ostatné uzly
     *
     * **/


    public void relayData(String jsonData, String action) {
        List<Node> nodes = Node.all
        nodes.each { Node node ->
            relayDataToNode(jsonData, node, action)
        }
    }

    /**
     * Metóda ktorá vykoná zaslanie dát o tovare na jeden uzol,
     * v prípade neúspechu vytvorí záznam entity RestAsyncPostRequest v databáze
     * a následne sa bude aplikácia periodicky snažiť túto požiadavku opakovať
     * kým ju daný uzol neprijme
     *
     * **/


    public void relayDataToNode(String jsonData, Node node, String action) {
        Boolean success = restAsyncPostRequestService.makePostRequest(jsonData, node, action)
        if(!success) {
            restAsyncPostRequestService.createAsyncPostRequestService(jsonData, node, action)
        }
    }
}
