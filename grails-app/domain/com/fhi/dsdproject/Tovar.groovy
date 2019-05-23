package com.fhi.dsdproject

class Tovar {

    def grailsApplication

    Long id
    String globalId // unique global identifier
    String nazov
    String vyrobca
    String popis
    String farba
    String cena
    String kod

    Date dateCreated
    Date lastUpdated

    Date lastLocalUpdate
    Long globalVersion = 0

    static constraints = {
        globalId nullable: true, unique: true
        lastLocalUpdate nullable: true
    }

    def afterInsert() {
        this.globalId = grailsApplication.config.dsdproject.nodeName + "-" + this.id
    }

}
