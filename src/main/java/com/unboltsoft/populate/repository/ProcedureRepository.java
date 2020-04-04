package com.unboltsoft.populate.repository;

import com.unboltsoft.populate.model.Procedure;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ProcedureRepository extends ReactiveMongoRepository<Procedure, ObjectId> {
    @Query(value = "{'description': ?0}", delete = true)
    public Mono<ServerResponse> deleteAll(String description);
}
