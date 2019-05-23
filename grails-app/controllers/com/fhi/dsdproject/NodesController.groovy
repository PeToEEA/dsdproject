package com.fhi.dsdproject

class NodesController {

    def nodesService

    def index() {
        List<Node> nodes = Node.all
        render view: 'nodes', model: [nodes: nodes]
    }

    def detail() {
        Long id = params.getLong("id")
        render view: "detail", model: [node: Node.findById(id)]
    }

    def delete() {
        Long id = params.getLong("id")
        Boolean success = nodesService.delete(id)
        redirect action: "index", params: [deleted: success]
    }

    def doEdit(NodeEditCmd nodeEditCmd) {
        Long id = nodesService.edit(nodeEditCmd)
        redirect action: 'detail', params: [id: id]
    }
}
