package com.fhi.dsdproject

import grails.transaction.Transactional

@Transactional
class NodesService {

    def restAsyncPostRequestService

    public Boolean delete(Long id) {
        Node node = Node.findById(id)
        if(node) {
            node.delete()
            return true
        }
        return false
    }

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

    public void relayData(String jsonData) {
        List<Node> nodes = Node.all
        nodes.each { Node node ->
            relayDataToNode(jsonData, node)
        }
    }

    public void relayDataToNode(String jsonData, Node node) {
        Boolean success = restAsyncPostRequestService.makePostRequest(jsonData, node)
        if(!success) {
            restAsyncPostRequestService.createAsyncPostRequestService(jsonData, node)
        }
    }
}
