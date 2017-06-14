package lv.ctco.cukesgraphql.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukesgraphql.facade.GQLResponseFacade;

@Singleton
public class WhenSteps {

    @Inject
    GQLResponseFacade facade;

}
