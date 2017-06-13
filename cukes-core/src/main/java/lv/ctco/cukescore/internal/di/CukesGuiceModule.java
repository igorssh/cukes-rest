package lv.ctco.cukescore.internal.di;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import lv.ctco.cukescore.CukesOptions;
import lv.ctco.cukescore.CukesRestPlugin;
import lv.ctco.cukescore.CukesRuntimeException;
import lv.ctco.cukescore.internal.AssertionFacade;
import lv.ctco.cukescore.internal.AssertionFacadeImpl;
import lv.ctco.cukescore.internal.VariableFacade;
import lv.ctco.cukescore.internal.VariableFacadeImpl;
import lv.ctco.cukescore.internal.context.CaptureContext;
import lv.ctco.cukescore.internal.context.CaptureContextInterceptor;
import lv.ctco.cukescore.internal.context.GlobalWorld;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.internal.context.InflateContextInterceptor;
import lv.ctco.cukescore.internal.logging.HttpLoggingPlugin;
import lv.ctco.cukescore.internal.switches.SwitchedBy;
import lv.ctco.cukescore.internal.switches.SwitchedByInterceptor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Properties;

import static lv.ctco.cukescore.internal.AssertionFacade.ASSERTION_FACADE;
import static lv.ctco.cukescore.internal.VariableFacade.VARIABLE_FACADE;

public class CukesGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bindInterceptor(new InflateContextInterceptor(), InflateContext.class);
        bindInterceptor(new CaptureContextInterceptor(), CaptureContext.class);
        bindInterceptor(new SwitchedByInterceptor(), SwitchedBy.class);

        bindAssertionFacade();
        bindVariableFacade();
        bindPlugins();
    }

    @SuppressWarnings("unchecked")
    private void bindAssertionFacade() {
        bindAlternative(ASSERTION_FACADE, AssertionFacade.class, AssertionFacadeImpl.class);
    }

    @SuppressWarnings("unchecked")
    private void bindVariableFacade() {
        bindAlternative(VARIABLE_FACADE, VariableFacade.class, VariableFacadeImpl.class);
    }

    private <T, E extends T> void bindAlternative(String type, Class<T> clazz, Class<E> defaultClass) {
        String alternativeType = System.getProperty(type);
        Class<? extends T> targetClass;
        if (alternativeType == null || alternativeType.isEmpty()) {
            targetClass = defaultClass;
        } else {
            try {
                targetClass = (Class<T>) Class.forName(alternativeType);
            } catch (ClassNotFoundException e) {
                throw new CukesRuntimeException("Invalid " + type + " value", e);
            } catch (ClassCastException e) {
                throw new CukesRuntimeException("Invalid " + type + " value", e);
            }
        }
        bind(clazz).to(targetClass);
    }

    private void bindInterceptor(MethodInterceptor interceptor, Class<? extends Annotation> annotationType) {
        requestInjection(interceptor);
        bindInterceptor(Matchers.annotatedWith(annotationType), Matchers.any(), interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(annotationType), interceptor);
    }

    @SuppressWarnings("unchecked")
    private void bindPlugins() {
        try {
            Multibinder<CukesRestPlugin> multibinder = Multibinder.newSetBinder(binder(), CukesRestPlugin.class);

            // add our own plugins
            multibinder.addBinding().to(HttpLoggingPlugin.class);

            // add user configured plugins
            ClassLoader classLoader = CukesGuiceModule.class.getClassLoader();
            Properties prop = new Properties();
            URL url = createCukesPropertyFileUrl(classLoader);
            if (url == null) return;
            prop.load(url.openStream());
            String pluginsArr = prop.getProperty(CukesOptions.PROPERTIES_PREFIX + CukesOptions.PLUGINS);
            if (pluginsArr == null) return;
            String[] pluginClasses = pluginsArr.split(CukesOptions.DELIMITER);
            for (String pluginClass : pluginClasses) {
                Class<? extends CukesRestPlugin> aClass = (Class<? extends CukesRestPlugin>) classLoader.loadClass(pluginClass);
                multibinder.addBinding().to(aClass);
            }
        } catch (Exception e) {
            throw new CukesRuntimeException("Binding of CukesRest plugins failed");
        }
    }

    /**
     * @see GlobalWorld#createCukesPropertyFileUrl(ClassLoader)
     */
    private URL createCukesPropertyFileUrl(final ClassLoader classLoader) {
        String cukesProfile = System.getProperty("cukes.profile");
        String propertiesFileName = cukesProfile == null || cukesProfile.isEmpty()
            ? "cukes.properties"
            : "cukes-" + cukesProfile + ".properties";
        return classLoader.getResource(propertiesFileName);
    }
}
