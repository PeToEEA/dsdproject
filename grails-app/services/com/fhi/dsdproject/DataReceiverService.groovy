package com.fhi.dsdproject

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class DataReceiverService {

    public void process(String jsonString) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonData = jsonSlurper.parseText(jsonString)
    }
}
