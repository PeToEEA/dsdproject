package com.fhi.dsdproject

import grails.transaction.Transactional
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

@Transactional
class DataReceiverService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSSS")

    /**
     * Metóda ktorí prevedie teztový JSON na objekt ktorý
     * obsahuje dáta o tovare zaslané z iného uzla
     *
     * **/

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


    /**
     * Metóda ktorá na základe globalId overí či záznam už existuje a podľa
     * toho ho buď upraví alebo vytvorí nový záznam
     *
     * **/

    public Tovar updateOrCreateNewTovar(TovarDto tovarDto) {
        Tovar tovar = Tovar.findByGlobalId(tovarDto.globalId)
        if(tovar) {
            tovar.updateFromDto(tovarDto)
        } else {
            tovar = new Tovar()
            tovar.createFromDto(tovarDto)
        }
        return tovar
    }

    /**
     * Metóda na zmazanie záznamu, podľa globalId overí
     * existenciu záznamu a zmaže ho
     *
     * **/


    public void processDelete(String jsonString) {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def jsonData = jsonSlurper.parseText(jsonString).target
        if(jsonData.getAt("class") == "com.fhi.dsdproject.Tovar") {
            String globalId = jsonData.getAt("globalId")
            Tovar tovar = Tovar.findByGlobalId(globalId)
            if(tovar) {
                tovar.delete()
            } else {
                log.warn("Ziadny tovar pre globalId $globalId")
            }
        }
    }
}
