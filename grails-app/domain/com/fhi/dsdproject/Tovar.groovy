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

    public void createFromDto(TovarDto tovarDto) {
        copyPropertiesFromDto(tovarDto)
        this.save(failOnError: true)
    }

    public void updateFromDto(TovarDto tovarDto) {
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
        this.globalVersion = tovarDto.globalVersion
    }

}
