package com.fhi.dsdproject

/**
* ORM Entita
* Reprezentuje zaznam o uzle.
*
**/

class Node {

    Long id
    String name
    String url

    Date lastDatabaseUpdate

    Date dateCreated
    Date lastUpdated


    static constraints = {
        lastDatabaseUpdate nullable: true
    }
}
