package com.fhi.dsdproject

/**
 * Kontroler na spravovanie tovarov.
 *
 * **/

class TovarController {

    def tovarService

    def index() {
        List<Tovar> tovary = Tovar.all
        Boolean deleted = params.getBoolean("deleted")
        render view: 'tovary', model: [tovary: tovary, deleted: deleted]
    }

    def detail() {
        Long id = params.getLong("id")
        render view: "detail", model: [tovar: Tovar.findById(id)]
    }

    def delete() {
        Long id = params.getLong("id")
        Boolean success = tovarService.delete(id)
        redirect action: "index", params: [deleted: success]
    }

    def doEdit(TovarEditCmd tovarEditCmd) {
        Long id = tovarService.edit(tovarEditCmd)
        redirect action: 'detail', params: [id: id]
    }
}
