package board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleLogTest {

    private static final Logger log = LoggerFactory.getLogger(SampleLogTest.class);

    public void testLog() {
        log.debug("log test");
    }

}
