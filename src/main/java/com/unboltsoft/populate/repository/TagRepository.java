package com.unboltsoft.populate.repository;

import com.unboltsoft.populate.model.Tag;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TagRepository extends ReactiveMongoRepository<Tag, ObjectId> {
}
