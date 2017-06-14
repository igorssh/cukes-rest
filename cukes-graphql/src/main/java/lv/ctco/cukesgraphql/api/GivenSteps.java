package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.Given;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.resources.ResourceFileReader;
import lv.ctco.cukesgraphql.facade.GQLRequestFacade;

@Singleton
public class GivenSteps {

    @Inject
    private GQLRequestFacade facade;

    @Inject
    private GlobalWorldFacade world;

    @Inject
    private ResourceFileReader fileReader;

    @Given("^query from file \"(.+)\"$")
    public void request_Body_From_File(String path) {
        this.facade.body(this.fileReader.read(path));
    }

}
