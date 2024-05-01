package com.faboda.mailblitz.repository;

import com.faboda.mailblitz.model.CurlASTRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurlASTRequestRepository extends MongoRepository<CurlASTRequest,String> {

}
