package lv.ctco.cukesgraphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.Response;
import lv.ctco.cukescore.extension.CukesRestPlugin;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;

import java.util.Set;

@Singleton
@InflateContext
public class GQLResponseFacade {

    @Inject
    GQLRequestFacade specification;

    @Inject
    GlobalWorldFacade world;

    @Inject
    Set<CukesRestPlugin> pluginSet;

    private Response response;

}
