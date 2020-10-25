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
public class ScuullRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScuullRouterApplication.class, args);
	}

}
