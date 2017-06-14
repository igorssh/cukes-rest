package lv.ctco.cukescore.internal;

import cucumber.api.java.ObjectFactory;
import lv.ctco.cukescore.internal.di.SingletonObjectFactory;

public class IntegrationTestBase {

    private ObjectFactory objectFactory = SingletonObjectFactory.instance();

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }
}
