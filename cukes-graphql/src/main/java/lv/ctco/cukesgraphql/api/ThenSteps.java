package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.internal.resources.ResourceFileReader;
import lv.ctco.cukesgraphql.facade.GQLAssertionFacade;

@Singleton
public class ThenSteps {

    @Inject
    private GQLAssertionFacade assertionFacade;

    @Inject
    private ResourceFileReader fileReader;

}
