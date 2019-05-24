package com.fhi.dsdproject

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
