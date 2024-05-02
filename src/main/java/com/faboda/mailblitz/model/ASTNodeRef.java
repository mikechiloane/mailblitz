package com.faboda.mailblitz.model;

import com.faboda.curl.ast.NodeType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "ast_node_ref")
public class ASTNodeRef {
  @Id private String id;
  private NodeType type;
  private String value;
  private String parent;
  private String key;
  private String ref;
}
