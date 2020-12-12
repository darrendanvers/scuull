package dev.codestijl.scuullrouter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the application context loads.
 *
 * @author darren
 * @since 1.0.0
 */
@SpringBootTest
class ScuullRouterApplicationTests {

    /**
     * Tests the application context loads.
     */
    @Test
    // This is a a false warning, as this test will fail if
    // the context does not load.
    @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
    public void contextLoads() {
        // Intentionally empty.
    }
}
