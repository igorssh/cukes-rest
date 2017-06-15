package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.Then;
import lv.ctco.cukesgraphql.facade.GQLAssertionFacade;

@Singleton
public class ThenSteps {

    @Inject
    private GQLAssertionFacade assertionFacade;

    @Then("^response contains property \"(.+)\" with value \"(.*)\"$")
    public void response_Body_Contains_Property(String path, String value) {
        this.assertionFacade.responseContainsPropertyWithValue(path, value);
    }

    @Then("^response contains an array \"(.+)\" of size (>=|>|<=|<|<>) (\\d+)$")
    public void response_Body_Contains_Array_With_Operator_Size(String path, String operator, Integer size) {
        this.assertionFacade.bodyContainsArrayWithSize(path, operator + size);
    }

    @Then("^response contains an array \"(.+)\" of size (\\d+)$")
    public void response_Body_Contains_Array_With_Size(String path, Integer size) {
        this.assertionFacade.bodyContainsArrayWithSize(path, size.toString());
    }
}
