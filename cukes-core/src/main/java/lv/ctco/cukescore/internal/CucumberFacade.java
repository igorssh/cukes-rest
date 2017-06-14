package lv.ctco.cukescore.internal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.extension.CukesRestPlugin;
import lv.ctco.cukescore.internal.context.GlobalWorldFacade;

import java.util.Set;

@Singleton
public class CucumberFacade {

    /* Ugly Hack proposed by Cucumber developers: https://github.com/cucumber/cucumber-jvm/pull/295 */
    private static boolean firstRun = true;

    @Inject
    GlobalWorldFacade world;

    @Inject
    Set<CukesRestPlugin> pluginSet;


    public boolean firstScenario() {
        return firstRun;
    }

    public void beforeAllTests() {
        firstRun = false;
        for (CukesRestPlugin cukesRestPlugin : pluginSet) {
            cukesRestPlugin.beforeAllTests();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                afterAllTests();
            }
        });
    }

    public void beforeScenario() {
        world.reconstruct();
        for (CukesRestPlugin cukesRestPlugin : pluginSet) {
            cukesRestPlugin.beforeScenario();
        }
    }

    public void afterScenario() {
        for (CukesRestPlugin cukesRestPlugin : pluginSet) {
            cukesRestPlugin.afterScenario();
        }
    }

    public void afterAllTests() {
        for (CukesRestPlugin cukesRestPlugin : pluginSet) {
            cukesRestPlugin.afterAllTests();
        }
    }
}