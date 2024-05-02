package com.faboda.mailblitz.repository;

import com.faboda.mailblitz.model.ASTNodeRef;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ASTNodeRefRepository extends MongoRepository<ASTNodeRef, String> {

  List<ASTNodeRef> findByParent(String parent);

  Optional<ASTNodeRef> findByRef(String ref);
}
