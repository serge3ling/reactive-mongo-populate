package com.unboltsoft.populate.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ProcedureRouter {
    @NonNull
    private final ProcedureHandler procedureHandler;

    public ProcedureRouter(ProcedureHandler procedureHandler) {
        this.procedureHandler = procedureHandler;
    }

    @NonNull
    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(RequestPredicates.GET("/insert"), procedureHandler::insertProcedures)
                .andRoute(RequestPredicates.GET("/delete"), procedureHandler::deleteProcedures)
                .andRoute(RequestPredicates.GET("/"), procedureHandler::welcome);
    }
}
