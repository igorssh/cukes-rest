package lv.ctco.cukesrest.loadrunner;

import com.google.inject.AbstractModule;
import lv.ctco.cukescore.extension.CukesInjectableModule;
import lv.ctco.cukescore.internal.AssertionFacade;
import lv.ctco.cukesrest.VariableFacade;

@CukesInjectableModule
public class CukesRestLoadRunnerGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AssertionFacade.class).to(AssertionFacadeLoadRunnerImpl.class);
        bind(VariableFacade.class).to(VariableFacadeLoadRunnerImpl.class);
    }
}
