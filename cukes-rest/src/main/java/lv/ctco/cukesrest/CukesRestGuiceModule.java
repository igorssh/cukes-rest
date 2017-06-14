package lv.ctco.cukesrest;

import com.google.inject.AbstractModule;
import lv.ctco.cukescore.extension.CukesInjectableModule;
import lv.ctco.cukescore.internal.AssertionFacade;
import lv.ctco.cukescore.internal.AssertionFacadeImpl;

@CukesInjectableModule
public class CukesRestGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AssertionFacade.class).to(AssertionFacadeImpl.class);
        bind(VariableFacade.class).to(VariableFacadeImpl.class);
    }
}
