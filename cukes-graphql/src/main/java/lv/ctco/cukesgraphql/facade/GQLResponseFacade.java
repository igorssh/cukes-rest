package lv.ctco.cukesgraphql.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.extension.CukesRestPlugin;
import lv.ctco.cukescore.internal.HttpMethod;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.switches.ResponseWrapper;

import java.util.Set;
import java.util.concurrent.Callable;

@Singleton
@InflateContext
public class GQLResponseFacade {

    @Inject
    GQLRequestFacade requestFacade;

    @Inject
    GlobalWorldFacade world;

    @Inject
    Set<CukesRestPlugin> pluginSet;

    private Response response;

    public void doRequest() throws Exception {
        requestFacade.body(requestFacade.getGraphQLRequest());
        doRequest(HttpMethod.POST).call();

        requestFacade.initNewSpecification();
    }

    private Callable<ResponseWrapper> doRequest(final HttpMethod method) {
        return new Callable<ResponseWrapper>() {
            @Override
            public ResponseWrapper call() throws Exception {
                final RequestSpecification requestSpec = requestFacade.value();

                for (CukesRestPlugin cukesRestPlugin : pluginSet) {
                    cukesRestPlugin.beforeRequest(requestSpec);
                }

                response = method.doRequest(requestSpec);

                System.out.println(response.asString());

                for (CukesRestPlugin cukesRestPlugin : pluginSet) {
                    cukesRestPlugin.afterRequest(response);
                }
                cacheHeaders(response);
                return new ResponseWrapper(response);
            }
        };
    }

    private void clearOldHeaders() {
        Set<String> keys = world.getKeysStartingWith(CukesOptions.HEADER_PREFIX);
        for (String key : keys) {
            world.remove(key);
        }
    }

    private void cacheHeaders(Response response) {
        clearOldHeaders();
        Headers headers = response.getHeaders();
        for (Header header : headers) {
            String headerName = CukesOptions.HEADER_PREFIX + header.getName();
            world.put(headerName, header.getValue());
        }
    }

}
