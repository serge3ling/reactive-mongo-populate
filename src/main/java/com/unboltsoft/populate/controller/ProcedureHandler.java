package com.unboltsoft.populate.controller;

import com.unboltsoft.populate.config.ConfigProperties;
import com.unboltsoft.populate.model.Procedure;
import com.unboltsoft.populate.repository.ProcedureRepository;
import com.unboltsoft.populate.service.ProcedureMaker;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ProcedureHandler {
    @NonNull
    private final ProcedureRepository procedureRepository;
    @NonNull
    private final ConfigProperties configProperties;

    public ProcedureHandler(ProcedureRepository procedureRepository, ConfigProperties configProperties) {
        this.procedureRepository = procedureRepository;
        this.configProperties = configProperties;
    }

    @NonNull
    public Mono<ServerResponse> insertProcedures(ServerRequest request) {
        int count = 1;
        try {
            count = Integer.parseInt(request.queryParam("count").orElse("1"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Procedure> list = new ProcedureMaker(count, configProperties).make();

        return ServerResponse.ok().body(procedureRepository.insert(list).count(), Long.class);
    }

    @NonNull
    public Mono<ServerResponse> deleteProcedures(ServerRequest request) {
        return procedureRepository.deleteAll(configProperties.getFakeDescription())
                .flatMap(item -> ServerResponse.ok().build());
    }
}
