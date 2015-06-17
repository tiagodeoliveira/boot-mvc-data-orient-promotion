package io.tiagodeoliveira

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
 * Created by tiago on 16/06/15.
 */

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = Application)
@WebAppConfiguration
class ApplicationTest extends GroovyTestCase {

    @Test
    void testMain() {

    }
}
