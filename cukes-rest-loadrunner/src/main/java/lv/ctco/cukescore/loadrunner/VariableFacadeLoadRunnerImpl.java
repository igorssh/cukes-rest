package lv.ctco.cukescore.loadrunner;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.ctco.cukescore.internal.context.InflateContext;
import lv.ctco.cukescore.loadrunner.function.LoadRunnerFunction;
import lv.ctco.cukesrest.VariableFacade;

@Singleton
@InflateContext
public class VariableFacadeLoadRunnerImpl implements VariableFacade {

    @Inject
    LoadRunnerFilter loadRunnerFilter;

    @Override
    public void setVariable(final String name, final String value) {
        loadRunnerFilter.getTrx().addFunction(new LoadRunnerFunction() {
            @Override
            public String format() {
                return "lr_set_string(\"" + name + "\", \"" + value + "\");\n";
            }
        });
    }

    @Override
    public void setUUIDToVariable(final String name) {
        loadRunnerFilter.getTrx().addFunction(new LoadRunnerFunction() {
            @Override
            public String format() {
                return "random_Generator(\"" + name + "\", 32);\n";
            }
        });
    }

    @Override
    public void setCurrentTimestampToVariable(final String name) {
        loadRunnerFilter.getTrx().addFunction(new LoadRunnerFunction() {
            @Override
            public String format() {
                return "lr_save_timestamp(\"" + name + "\", \"DIGITS=10\", LAST);";
            }
        });
    }
}
