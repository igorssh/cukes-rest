package lv.ctco.cukescore.loadrunner;

import com.google.inject.AbstractModule;
import lv.ctco.cukescore.extension.CukesInjectableModule;
import lv.ctco.cukesrest.VariableFacade;
import lv.ctco.cukesrest.facade.AssertionFacade;

import static lv.ctco.cukescore.CukesOptions.LOADRUNNER_FILTER_BLOCKS_REQUESTS;
import static lv.ctco.cukescore.CukesOptions.PROPERTIES_PREFIX;

@CukesInjectableModule
public class CukesRestLoadRunnerGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        boolean isLoadRunnedEnabled = Boolean.valueOf(System.getProperty(PROPERTIES_PREFIX + LOADRUNNER_FILTER_BLOCKS_REQUESTS));
        if (isLoadRunnedEnabled) {
            bind(AssertionFacade.class).to(AssertionFacadeLoadRunnerImpl.class);
            bind(VariableFacade.class).to(VariableFacadeLoadRunnerImpl.class);
        }
    }
}
