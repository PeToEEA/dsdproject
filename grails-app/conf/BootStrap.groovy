import com.fhi.dsdproject.*

class BootStrap {

    def init = { servletContext ->
        initTovar()
    }
    def destroy = {
    }

    public void initTovar() {
        for(int i=0;i<100;i++) {
            addTovar("test" + i)
        }
    }

    public void addTovar(String testProperyName) {
        Tovar tovar = Tovar.findByKod(testProperyName)
        if (!tovar) {
            tovar = new Tovar(nazov: testProperyName, vyrobca: testProperyName, popis: testProperyName, farba: testProperyName, cena: testProperyName, kod: testProperyName, lastLocalUpdate: new Date()).save(failOnError: true)
        }
    }
}
