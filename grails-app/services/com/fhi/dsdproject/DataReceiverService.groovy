package com.fhi.dsdproject

import grails.transaction.Transactional
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

@Transactional
class DataReceiverService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSSS")

    public void process(String jsonString) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonData = jsonSlurper.parseText(jsonString).target
        if(jsonData.getAt("class") == "com.fhi.dsdproject.Tovar") {
            TovarDto tovarDto = mapJsonToDto(jsonData)
            updateOrCreateNewTovar(tovarDto)
        }
    }

    public TovarDto mapJsonToDto(def jsonData) {
        TovarDto tovarDto = new TovarDto()
        tovarDto.id = jsonData.id
        tovarDto.nazov = jsonData.nazov
        tovarDto.kod = jsonData.kod
        tovarDto.cena = jsonData.cena
        tovarDto.farba = jsonData.farba
        tovarDto.globalId = jsonData.globalId
        tovarDto.globalVersion = jsonData.globalVersion
        tovarDto.popis = jsonData.popis
        tovarDto.vyrobca = jsonData.vyrobca
        tovarDto.dateCreated = dateFormat.parse(jsonData.dateCreated as String)
        tovarDto.lastUpdated = dateFormat.parse(jsonData.lastUpdated as String)
        tovarDto.lastLocalUpdate = dateFormat.parse(jsonData.lastLocalUpdate as String)
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
