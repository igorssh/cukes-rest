package lv.ctco.cukesgraphql;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    format = {"pretty", "json:target/cucumber.json", "lv.ctco.cukescore.formatter.CukesRestJsonFormatter:target/cucumber2.json"},
    features = {"classpath:features/gadgets/"},
    glue = "lv.ctco.cukesgraphql.api",
    strict = true
)
public class RunCukesGraphQLTest {

    @BeforeClass
    public static void setUp() throws Exception {
        new SampleApplication().run(new String[]{"server", "server.yml"});
    }
}
