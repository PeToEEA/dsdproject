package com.fhi.dsdproject

import java.text.SimpleDateFormat

class Tovar {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSSS")

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

    public void createFromDto(TovarDto tovarDto) {
        copyPropertiesFromDto(tovarDto)
        this.save(failOnError: true)
    }

    public void updateFromDto(TovarDto tovarDto) {
        if(tovarDto.globalVersion == this.globalVersion) {
            return
        }
        copyPropertiesFromDto(tovarDto)
        this.save(failOnError: true)
    }

    private void copyPropertiesFromDto(TovarDto tovarDto) {
        this.nazov = tovarDto.nazov
        this.vyrobca = tovarDto.vyrobca
        this.popis = tovarDto.popis
        this.farba = tovarDto.farba
        this.cena = tovarDto.cena
        this.kod = tovarDto.kod
        this.dateCreated = tovarDto.dateCreated
        this.lastUpdated = tovarDto.lastUpdated
        this.lastLocalUpdate = tovarDto.lastLocalUpdate
        this.globalId = tovarDto.globalId
        this.globalVersion = tovarDto.globalVersion
    }

    public Map<String,Object> toMap() {
        return [
                id: this.id,
                class: this.class,
                globalId: this.globalId,
                nazov: this.nazov,
                vyrobca: this.vyrobca,
                popis: this.popis,
                farba: this.farba,
                cena: this.cena,
                kod: this.kod,
                dateCreated: dateFormat.format(this.dateCreated),
                lastUpdated: dateFormat.format(this.lastUpdated),
                lastLocalUpdate: dateFormat.format(this.lastLocalUpdate),
                globalVersion: this.globalVersion
        ]
    }

}
