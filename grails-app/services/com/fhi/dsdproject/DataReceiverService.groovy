package com.fhi.dsdproject

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class DataReceiverService {

    public void process(String jsonString) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonData = jsonSlurper.parseText(jsonString)
        if(jsonData.getAt("class") == "com.fhi.dsdproject.Tovar") {
            TovarDto tovarDto = mapJsonToDto(jsonData)
            updateOrCreateNewTovar(tovarDto)
        }
    }

    public TovarDto mapJsonToDto(def jsonData) {
        TovarDto tovarDto = new TovarDto()
        tovarDto.id = jsonData.id
        tovarDto.kod = jsonData.kod
        tovarDto.cena = jsonData.cena
        tovarDto.farba = jsonData.farba
        tovarDto.globalId = jsonData.globalId
        tovarDto.popis = jsonData.popis
        tovarDto.vyrobca = jsonData.vyrobca
        tovarDto.dateCreated = jsonData.dateCreated
        tovarDto.lastUpdated = jsonData.lastUpdated
        tovarDto.lastLocalUpdate = jsonData.lastLocalUpdate
        return tovarDto
    }

    public Tovar updateOrCreateNewTovar(TovarDto tovarDto) {
        Tovar tovar = Tovar.findByGlobalId(tovarDto.globalId)
        if(tovar) {
            tovar.updateFromDto(tovarDto)
        } else {
            tovar = new Tovar()
            tovar.createFromDto(tovarDto)
        }
    }
}
