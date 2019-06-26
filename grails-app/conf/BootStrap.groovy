import org.hibernate.Session

class BootStrap {

    def init = { servletContext ->
        addDirty()
    }
    def destroy = {
    }


    public void addDirty() {
        Session.metaClass.isDirty = { ->
            delegate.persistenceContext.entitiesByKey.values().any { it.isDirty() }
        }
    }
}
