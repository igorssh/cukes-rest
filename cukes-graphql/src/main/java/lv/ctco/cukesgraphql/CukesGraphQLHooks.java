package lv.ctco.cukesgraphql;

import com.google.inject.Inject;
import cucumber.api.java.After;
import lv.ctco.cukesgraphql.facade.GQLRequestFacade;

public class CukesGraphQLHooks {

    @Inject
    GQLRequestFacade requestFacade;

    @After
    public void afterScenario() {
        requestFacade.initNewSpecification();
    }
}
