package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.resources.ResourceFileReader;
import lv.ctco.cukesgraphql.facade.GQLRequestFacade;

@Singleton
public class GivenSteps {

    @Inject
    GQLRequestFacade facade;

    @Inject
    GlobalWorldFacade world;

    @Inject
    private ResourceFileReader fileReader;

}
