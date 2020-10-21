package dev.codestijl.scuull;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application driver.
 *
 * @author darren
 * @since 1.0.0
 */
@SpringBootApplication
@EnableBatchProcessing
// Much of the structure of this class is driven by Spring, so ignoring this warning.
@SuppressWarnings("PMD.UseUtilityClass")
public class ScuullApplication {

    /**
     * Runs the application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ScuullApplication.class, args);
    }
}
