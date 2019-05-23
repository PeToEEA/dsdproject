package com.fhi.dsdproject

class TovarRelay {

    Long id

    String globalId
    String nazov
    String vyrobca
    String popis
    String farba
    String cena
    String kod  // unique global identifier

    Node node

    Date changeTime
    Date lastRelayAttempt = null
    Date acceptTime = null

    Integer attemptsRemaining = 10

    static constraints = {
        lastRelayAttempt nullable: true
        acceptTime nullable: true
    }

    public TovarRelay() {}

    public TovarRelay(Tovar tovar, Node node) {
        this.globalId = tovar.globalId
        this.nazov = tovar.nazov
        this.vyrobca = tovar.vyrobca
        this.popis = tovar.popis
        this.farba = tovar.farba
        this.cena = tovar.cena
        this.kod = tovar.kod
        this.changeTime = tovar.lastLocalUpdate
        this.node = node
    }
}
