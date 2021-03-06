package com.fhi.dsdproject

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class TovarService {

    def grailsApplication
    def nodesService

    /**
     * Metóda na zmazanie záznamu o tovare
     * vyvolaná lokálne užívateľom tohto uzla
     *
     * **/

    public Boolean delete(Long id) {
        Tovar tovar = Tovar.findById(id)
        if(tovar) {
            nodesService.relayData((tovar.toMap() as JSON) as String, GlobalStrings.ACTION_DELETE)
            tovar.delete()
            return true
        }
        return false
    }

    /**
     * Metóda na úpravu alebo vytvorenie záznamu o tovare
     * vyvolaná lokálne užívateľom tohto uzla
     *
     * **/

    public Long edit(TovarEditCmd tovarEditCmd) {
        Tovar tovar = null
        if(tovarEditCmd.id) {
            tovar = Tovar.findById(tovarEditCmd.id)
            copyProps(tovar, tovarEditCmd)
            tovar.save(failOnError: true)
        } else {
            tovar = new Tovar()
            copyProps(tovar, tovarEditCmd)
            tovar.save(failOnError: true)
        }
        nodesService.relayData((tovar.toMap() as JSON) as String, GlobalStrings.ACTION_INPUT)
        return tovar.id
    }

    /**
     * Metóda na preklopenie atribútov z
     * command objektu do entity
     *
     * **/

    private void copyProps(Tovar tovar, TovarEditCmd tovarEditCmd) {
        tovar.nazov = tovarEditCmd.nazov
        tovar.vyrobca = tovarEditCmd.vyrobca
        tovar.popis = tovarEditCmd.popis
        tovar.farba = tovarEditCmd.farba
        tovar.cena = tovarEditCmd.cena
        tovar.kod = tovarEditCmd.kod
        tovar.lastLocalUpdate = new Date()
        if(tovar.id) {
            tovar.globalVersion++
        }
    }
}
