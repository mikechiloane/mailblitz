package com.faboda.mailblitz.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "curl_ast_requests")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CurlASTRequest {
  @Id private String id;
  @Field private ASTNodeRef parent;
  @Field private List<ASTNodeRef> children;
  @Field private String dataVariable;
}
