package com.fhi.dsdproject

import grails.transaction.Transactional

@Transactional
class RelayTovarService {

    def sendUpdatesToNodes() {
        Date lastChange = Tovar.executeQuery("select lastUpdated from Tovar order by lastUpdated desc")
        List<Node> nodes = Node.all
        nodes.each { Node node ->

        }
    }

    private void sendUpdateToNode(Node node, Date lastChange) {
        Boolean needsUpdate = node.lastDatabaseUpdate
    }
}
