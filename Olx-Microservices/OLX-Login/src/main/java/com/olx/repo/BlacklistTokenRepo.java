package com.olx.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.BlacklistTokenDocument;

public interface BlacklistTokenRepo extends MongoRepository<BlacklistTokenDocument, ObjectId>{

	BlacklistTokenDocument findByTokenEquals(String token);
}
