package lv.ctco.cukesgraphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.json.JsonParser;
import lv.ctco.cukescore.internal.switches.SwitchedBy;

@Singleton
@SwitchedBy(CukesOptions.ASSERTIONS_DISABLED)
@InflateContext
public class GQLAssertionFacade {

    @Inject
    private GlobalWorldFacade world;

    @Inject
    private JsonParser jsonParser;

    @Inject
    GQLResponseFacade facade;

}
