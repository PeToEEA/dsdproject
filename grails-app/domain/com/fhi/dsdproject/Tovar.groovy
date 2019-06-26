package com.fhi.dsdproject

import java.text.SimpleDateFormat

/**
 * ORM Entita
 * Reprezentuje zaznam o tovare.
 *
 **/

class Tovar {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSSS")

    def grailsApplication

    /**
     * id - lokalne id zaznamu v databaze
     *
     * **/
    Long id

    /**
    * globalId - unikatne globalne ID pre celu siet
     *         - prideli ho ten uzol ktory dany zaznam vytvara v tvare {nazov uzla}-{lokalne id}
    * **/
    String globalId

    String nazov
    String vyrobca
    String popis
    String farba
    String cena
    String kod

    Date dateCreated
    Date lastUpdated

    Date lastLocalUpdate


    /**
     *
     * globalVersion - verzia záznamu, začína od 0 pri vytvorení zaznamu
     *               - následne sa dvíha o 1 pri každej úprave záznamu
     *               - pričom verziu dvíha ten uzol ktorý vykonáva zmeny
     *
     * **/
    Long globalVersion = 0

    static constraints = {
        globalId nullable: true, unique: true
        lastLocalUpdate nullable: true
    }

    /**
     * metóda ktorá pridelí globálne id pri vytvorení záznamu
     * pozn. vo frameworku grails ktorý tu je použitý sa metóda afterInsert, ak je špecifikovaná
     * vykoná pred zápisom do databázy ale už je pridelené lokálne id z databá&y ktoré potrebné
     *
     * **/

    def afterInsert() {
        if(!this.globalId) {
            this.globalId = grailsApplication.config.dsdproject.nodeName + "-" + this.id
        }
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


    /**
     * funkcia na prekopírovanie dát z transfer objektu do entity
     *
     * **/

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

    /**
     *
     *  Táto metóda slúži na na prevedenie potrebných atribútov tovaru do mapy ktorá sa
     *  následne serializuje do JSON objektu.
     *
     * **/

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
