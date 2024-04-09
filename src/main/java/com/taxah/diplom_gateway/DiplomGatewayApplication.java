package com.taxah.diplom_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * The main class of the application. It is used to start the application.
 */
@SpringBootApplication
public class DiplomGatewayApplication {

    @Value("${path.database}")
    private String dataBasePath;

    @Value("${path.calculate}")
    private String calculatePath;


    public static void main(String[] args) {
        SpringApplication.run(DiplomGatewayApplication.class, args);
    }

    /**
     * Method for creating routes for the gateway.
     *
     * @param builder - the builder for creating routes.
     * @return - the created routes.
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("DataBase", r -> r.path("/db/**")
                        .uri("http://" + dataBasePath + ":8068/"))
                .route("CalculateSession", r -> r.path("/calc/**")
                        .uri("http://" + calculatePath + ":8070/"))
                .build();
    }
}
