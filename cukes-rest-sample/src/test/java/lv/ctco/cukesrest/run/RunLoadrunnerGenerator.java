package lv.ctco.cukesrest.run;

import cucumber.api.*;
import lv.ctco.cukescore.loadrunner.junit.*;
import org.junit.runner.*;

@RunWith(CucumberLoadRunner.class)
@CucumberOptions(
    format = {"pretty"},
    features = "classpath:features",
    glue = {"lv.ctco.cukesrest.api", "lv.ctco.cukesrest.loadrunner"},
    strict = true
)
public class RunLoadrunnerGenerator {}
