package lv.ctco.cukescore.loadrunner.dto;

import lv.ctco.cukescore.loadrunner.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LoadRunnerTransactionTest {

    @Test
    public void formatShouldEscapeWhitespaces() throws Exception {
        LoadRunnerTransaction trx = new LoadRunnerTransaction() {{
            setName("hello world");
            setTrxFlag("LR_AUTO");
        }};
        assertThat(trx.format(), containsString("hello_world"));
    }
}
