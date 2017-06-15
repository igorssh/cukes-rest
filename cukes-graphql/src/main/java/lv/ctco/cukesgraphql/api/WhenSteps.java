package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import lv.ctco.cukesgraphql.facade.GQLRequestFacade;
import lv.ctco.cukesgraphql.facade.GQLResponseFacade;

@Singleton
public class WhenSteps {

    @Inject
    private GQLRequestFacade requestFacade;

    @Inject
    private GQLResponseFacade responseFacade;

    @When("^the query is executed$")
    public void execute_Query() throws Throwable {
        String contentType = ContentType.JSON.toString();
        requestFacade.accept(contentType);
        requestFacade.contentType(contentType);
        responseFacade.doRequest();
    }
}
