package lv.ctco.cukesgraphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.response.ResponseBody;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.switches.SwitchedBy;
import org.hamcrest.Matcher;

import static lv.ctco.cukescore.internal.matchers.ArrayWithSizeMatcher.arrayWithSize;
import static lv.ctco.cukescore.internal.matchers.EqualToIgnoringTypeMatcher.equalToIgnoringType;
import static lv.ctco.cukescore.internal.matchers.JsonMatchers.containsPropertyValueByPathInArray;
import static lv.ctco.cukescore.internal.matchers.JsonMatchers.containsValueByPath;
import static org.junit.Assert.assertThat;

@Singleton
@SwitchedBy(CukesOptions.ASSERTIONS_DISABLED)
@InflateContext
public class GQLAssertionFacade {

    @Inject
    private GlobalWorldFacade world;

    @Inject
    GQLResponseFacade responseFacade;

    private String getPath(String path) {
        return "data." + path;
    }

    public void responseContainsPropertyWithValue(String path, String value) {
        assertBodyValueByPath(path, equalToIgnoringType(value, this.world.getBoolean("case-insensitive")));
    }

    public void bodyContainsArrayWithSize(String path, String size) {
        assertBodyValueByPath(path, arrayWithSize(size));
    }

    public void bodyContainsArrayWithObjectHavingProperty(String path, String property, String value) {
        ResponseBody responseBody = this.responseFacade.response().body();
        assertThat(responseBody, containsPropertyValueByPathInArray(getPath(path), property, equalToIgnoringType(value, this.world.getBoolean("case-insensitive")))
        );
    }

    private void assertBodyValueByPath(String path, Matcher matcher) {
        ResponseBody responseBody = this.responseFacade.response().body();
        assertThat(responseBody, containsValueByPath(getPath(path), matcher));
    }
}
