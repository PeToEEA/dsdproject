package com.fhi.dsdproject

class DataReceiverController {

    def dataReceiverService

    def index() { }

    def input() {
        String json = request.getJSON()
        dataReceiverService.process(json)
        render status: 200, text: 'OK'
    }
}
