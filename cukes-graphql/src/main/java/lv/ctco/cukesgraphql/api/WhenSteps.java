package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.When;
import lv.ctco.cukesgraphql.facade.GQLResponseFacade;

@Singleton
public class WhenSteps {

    @Inject
    private GQLResponseFacade facade;

    @When("^the query is executed$")
    public void execute_Query() throws Throwable {
//        facade.doRequest();
    }
}
