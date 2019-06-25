package com.fhi.dsdproject

class DataReceiverController {

    def dataReceiverService

    def index() { }


    /**
     *  Rest endpoint na prijmanie poziadaviek na pridanie novych zaznamov
     *  alebo upravu existujucich zaznamov (o tovare).
     *
     * ***/
    def input() {
        String json = request.reader.text
        log.info("Received json:\n${json}\n")
        dataReceiverService.process(json)
        render status: 200, text: 'OK'
    }


    /**
     *  To iste ako vyssie, len sluzi na zmazanie.
     * **/
    def delete() {
        String json = request.reader.text
        log.info("Received json:\n${json}\n")
        dataReceiverService.processDelete(json)
        render status: 200, text: 'OK'
    }
}
