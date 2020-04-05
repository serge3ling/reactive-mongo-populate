package com.unboltsoft.populate.repository;

import com.unboltsoft.populate.model.Step;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StepRepository extends ReactiveMongoRepository<Step, ObjectId> {
}
