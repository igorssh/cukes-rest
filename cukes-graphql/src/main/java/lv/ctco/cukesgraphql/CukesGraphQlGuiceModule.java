package lv.ctco.cukesgraphql;

import com.google.inject.AbstractModule;
import lv.ctco.cukescore.extension.CukesInjectableModule;
import lv.ctco.cukesrest.VariableFacade;
import lv.ctco.cukesrest.VariableFacadeImpl;
import lv.ctco.cukesrest.facade.AssertionFacade;
import lv.ctco.cukesrest.facade.AssertionFacadeImpl;

@CukesInjectableModule
public class CukesGraphQlGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AssertionFacade.class).to(AssertionFacadeImpl.class);
        bind(VariableFacade.class).to(VariableFacadeImpl.class);
    }
}
