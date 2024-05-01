package com.faboda.mailblitz.model;

import com.faboda.curl.ast.ASTNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "curl_ast_requests")
@Getter
@Setter
@Builder
public class CurlASTRequest {

  @Id private String id;
  private ASTNode astNode;
  private String dataVariable;
}
