package lv.ctco.cukesgraphql.facade;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.CukesRuntimeException;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.https.TrustAllTrustManager;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Singleton
@InflateContext
public class GQLRequestFacade {

    @Inject
    private GlobalWorldFacade world;

    private RequestSpecification specification;

    @Inject
    public GQLRequestFacade(GlobalWorldFacade world) {
        this.world = world;
        initNewSpecification();
    }

    public void initNewSpecification() {
        specification = RestAssured
            .given()
            .config(world.getRestAssuredConfig());
        onCreate();
    }

    private void onCreate() {
        Optional<String> $baseUri = world.get(CukesOptions.BASE_URI);
        if ($baseUri.isPresent()) {
            baseUri($baseUri.get());
        }

        Optional<String> $proxy = world.get(CukesOptions.PROXY);
        if ($proxy.isPresent()) {
            try {
                specification.proxy(new URI($proxy.get()));
            } catch (URISyntaxException ignore) {
                throw new CukesRuntimeException("Unable to set Proxy, please check the URL");
            }
        }

        if (world.getBoolean(CukesOptions.RELAXED_HTTPS)) {
            specification.relaxedHTTPSValidation();
            TrustAllTrustManager.trustAllHttpsCertificates();
        }
    }

    public void param(String key, Object value) {
        specification.param(key, value);
    }

    public void queryParam(String parameterName, String parameterValue) {
        try {
            parameterValue = URLEncoder.encode(parameterValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // do nothing
        }
        specification.queryParam(parameterName, parameterValue);
    }

    public void accept(String mediaTypes) {
        specification.accept(mediaTypes);
    }

    public void baseUri(String baseUri) {
        world.put(CukesOptions.BASE_URI, baseUri);
        specification.baseUri(baseUri);
    }

    public void contentType(String contentType) {
        specification.contentType(contentType);
    }

    public void formParam(String parameterName, String parameterValue) {
        specification.formParam(parameterName, parameterValue);
    }

    public void cookie(String cookieName, String cookieValue) {
        specification.cookie(cookieName, cookieValue);
    }

    public void header(String headerName, String headerValue) {
        specification.header(headerName, headerValue);
    }

    public void multiPart(String contentBody, String controlName) {
        specification.multiPart(controlName, contentBody);
    }

    public void multiPart(String contentBody, String controlName, String mimeType) {
        specification.multiPart(controlName, contentBody, mimeType);
    }

    public void proxy(String scheme, String host, Integer port) {
        if (port == null) port = 80;
        specification.proxy(host, port, scheme);
    }

    public void sessionId(String sessionIdValue) {
        specification.sessionId(sessionIdValue);
    }

    public void sessionId(String sessionIdName, String sessionIdValue) {
        specification.sessionId(sessionIdName, sessionIdValue);
    }

    public void body(String body) {
        specification.body(body);
    }

    public void authentication(String username, String password) {
        world.put(CukesOptions.USERNAME, username);
        world.put(CukesOptions.PASSWORD, password);
    }

    public void basicAuthentication(String username, String password) {
        specification.auth().basic(username, password);
    }

    public void authenticationType(String authenticationType) {
        world.put(CukesOptions.AUTH_TYPE, authenticationType);
    }

    public RequestSpecification value() {
        return specification;
    }
}
