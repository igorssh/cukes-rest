package lv.ctco.cukesgraphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.ResponseBody;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.matchers.ArrayWithSizeMatcher;
import lv.ctco.cukescore.internal.matchers.JsonMatchers;
import lv.ctco.cukescore.internal.switches.SwitchedBy;

import static lv.ctco.cukescore.internal.matchers.EqualToIgnoringTypeMatcher.equalToIgnoringType;
import static org.junit.Assert.assertThat;

@Singleton
@SwitchedBy(CukesOptions.ASSERTIONS_DISABLED)
@InflateContext
public class GQLAssertionFacade {

    private static final String PATH_PREFIX = "data.";

    @Inject
    private GlobalWorldFacade world;

    @Inject
    GQLResponseFacade responseFacade;

    public void responseContainsPropertyWithValue(String path, String value) {
        ResponseBody responseBody = this.responseFacade.response().body();
        assertThat(responseBody,
            JsonMatchers.containsValueByPath(PATH_PREFIX + path, equalToIgnoringType(value, this.world.getBoolean("case-insensitive"))));
    }

    public void bodyContainsArrayWithSize(String path, String size) {
        ResponseBody responseBody = this.responseFacade.response().body();
        assertThat(responseBody, JsonMatchers.containsValueByPath(PATH_PREFIX + path, ArrayWithSizeMatcher.arrayWithSize(size)));
    }
}
