package dev.codestijl.scuullrouter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Driver of the service.
 *
 * @author darren
 * @since 1.0.0
 */
@SpringBootApplication
@EnableZuulProxy
// Much of the structure of this class is driven by Spring, so ignoring this warning.
@SuppressWarnings("PMD.UseUtilityClass")
public class ScuullRouterApplication {

    /**
     * Runs the application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ScuullRouterApplication.class, args);
    }
}
