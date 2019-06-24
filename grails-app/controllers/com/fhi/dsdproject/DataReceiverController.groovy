package com.fhi.dsdproject

class DataReceiverController {

    def dataReceiverService

    def index() { }

    def input() {
        String json = request.reader.text
        log.info("Received json:\n${json}\n")
        dataReceiverService.process(json)
        render status: 200, text: 'OK'
    }

    def delete() {
        String json = request.reader.text
        log.info("Received json:\n${json}\n")
        dataReceiverService.processDelete(json)
        render status: 200, text: 'OK'
    }
}
