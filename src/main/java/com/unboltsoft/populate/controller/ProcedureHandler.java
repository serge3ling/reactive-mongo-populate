package com.unboltsoft.populate.controller;

import com.unboltsoft.populate.config.ConfigProperties;
import com.unboltsoft.populate.model.Procedure;
import com.unboltsoft.populate.repository.ProcedureRepository;
import com.unboltsoft.populate.repository.StepRepository;
import com.unboltsoft.populate.repository.TagRepository;
import com.unboltsoft.populate.service.ProcedureMaker;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProcedureHandler {
    @NonNull
    private final ProcedureRepository procedureRepository;
    @NonNull
    private final StepRepository stepRepository;
    @NonNull
    private final TagRepository tagRepository;
    @NonNull
    private final ConfigProperties configProperties;

    private ProcedureMaker procedureMaker;

    public ProcedureHandler(
            ProcedureRepository procedureRepository,
            StepRepository stepRepository,
            TagRepository tagRepository,
            ConfigProperties configProperties) {
        this.procedureRepository = procedureRepository;
        this.stepRepository = stepRepository;
        this.tagRepository = tagRepository;
        this.configProperties = configProperties;
    }

    @PostConstruct
    public void postConstruct() {
        procedureMaker = new ProcedureMaker(configProperties, stepRepository, tagRepository);
        procedureMaker.haveReady();
    }

    @NonNull
    public Mono<ServerResponse> insertProcedures(ServerRequest request) {
        int count = 1;
        try {
            count = Integer.parseInt(request.queryParam("count").orElse("1"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Procedure> list = procedureMaker.make(count);

        return ServerResponse.ok().body(procedureRepository.insert(list).count(), Long.class);
    }

    @NonNull
    public Mono<ServerResponse> deleteProcedures(ServerRequest request) {
        return procedureRepository.deleteAll(configProperties.getFakeTitle())
                .flatMap(item -> ServerResponse.ok().build());
    }

    @NonNull
    public Mono<ServerResponse> welcome(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Welcome."), String.class);
    }
}
