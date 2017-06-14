package lv.ctco.cukesgraphql;

import com.yammer.metrics.core.HealthCheck;

public class SampleHealthCheck extends HealthCheck {

    protected SampleHealthCheck() {
        super("sample");
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy(); // To Disable Dropwizard warnings
    }
}
