package com.fhi.dsdproject

class Node {

    Long id
    String ipAdress

    Date lastDatabaseUpdate


    Date dateCrated
    Date lastUpdated


    static constraints = {
        lastDatabaseUpdate nullable: true
    }
}
