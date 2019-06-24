package com.fhi.dsdproject

import grails.converters.JSON

class TestController {

    def restAsyncPostRequestService

    def index() { }


    def testAsyncExecutor() {
        //restAsyncPostRequestService.sendRequests()
        render "OK"
    }

    def tovarAsJson() {
        Tovar tovar = new Tovar(id: 111111, nazov: 'test1', farba: 'farba1', kod: 'testKod')
        render tovar as JSON
    }
}
