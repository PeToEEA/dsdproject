package com.fhi.dsdproject

import grails.transaction.Transactional

@Transactional
class TovarService {

    def grailsApplication

    public Boolean delete(Long id) {
        Tovar tovar = Tovar.findById(id)
        if(tovar) {
            tovar.delete()
            return true
        }
        return false
    }

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
        return tovar.id
    }

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
