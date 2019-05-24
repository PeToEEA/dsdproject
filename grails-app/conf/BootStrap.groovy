import com.fhi.dsdproject.*
import org.hibernate.Session

class BootStrap {

    def init = { servletContext ->
        addDirty()
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

    public void addDirty() {
        Session.metaClass.isDirty = { ->
            delegate.persistenceContext.entitiesByKey.values().any { it.isDirty() }
        }
    }
}
