package com.fhi.dsdproject

import grails.transaction.Transactional

@Transactional
class NodesService {

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
        node.ipAdress = nodeEditCmd.ipAdress
    }
}
