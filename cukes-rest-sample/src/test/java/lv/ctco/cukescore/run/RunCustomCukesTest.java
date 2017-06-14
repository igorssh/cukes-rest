package lv.ctco.cukescore.run;


import com.google.inject.Binder;
import com.google.inject.Module;
import cucumber.api.CucumberOptions;
import cucumber.api.guice.CucumberScopes;
import cucumber.api.junit.Cucumber;
import lv.ctco.cukescore.internal.di.SingletonObjectFactory;
import lv.ctco.cukescore.SampleApplication;
import lv.ctco.cukescore.run.custom.steps.CustomSteps;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    format = {"pretty"},
    features = "classpath:features/custom/",
    glue = {"lv.ctco.cukescore.api", "lv.ctco.cukescore.run.custom.steps"},
    strict = true
)
public class RunCustomCukesTest {

    @BeforeClass
    public static void setUp() throws Exception {
        SingletonObjectFactory.instance().addModule(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(CustomSteps.StateSaver.class).in(CucumberScopes.SCENARIO);
            }
        });

        new SampleApplication().run(new String[]{"server", "server.yml"});
    }
}
