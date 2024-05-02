package com.faboda.mailblitz.util;

import com.faboda.curl.ast.ASTNode;
import com.faboda.curl.ast.CurlASTBuilder;
import com.faboda.curl.ast.NodeType;
import com.faboda.curl.tokenizer.CurlTokenizer;
import com.faboda.curl.tokenizer.StandardCurlTokenizer;
import com.faboda.mailblitz.model.ASTNodeRef;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurlASTMutator {

  public static ASTNode curlToAST(String curlString) {
    System.out.println(curlString);
    CurlTokenizer curlTokenizer = new StandardCurlTokenizer(curlString);
    List<String> tokens = curlTokenizer.getTokens();
    return CurlASTBuilder.buildAST(tokens);
  }

  public static void setASTData(ASTNode astNode, String dataKey, String dataValue) {
    List<ASTNode> dataNodes =
        new java.util.ArrayList<>(
            astNode.getChildren().stream()
                .filter(astNode1 -> astNode1.getType().equals(NodeType.DATAPAIR))
                .toList());

    ASTNode targetNode =
        dataNodes.stream().filter(node -> node.getKey().equals(dataKey)).findFirst().get();
    int targetIndex = dataNodes.indexOf(targetNode);
    targetNode.setValue(dataValue);
    dataNodes.set(targetIndex, targetNode);
    astNode.resetChildren();
    astNode.addChildren(dataNodes);
  }

  public static List<ASTNodeRef> getAllASTNodeRefs(ASTNode astNode) {
    List<ASTNode> nodes = getAllASTNodes(astNode);
    List<ASTNodeRef> astNodeRefs = new java.util.ArrayList<>();
    for (ASTNode node : nodes) {
      astNodeRefs.add(astToASTNodeRef(node));
    }
    String parentId = generateRandomId();
    if (!astNodeRefs.isEmpty()) {
      astNodeRefs.get(0).setRef(parentId);

      for (ASTNodeRef astNodeRef : astNodeRefs) {
        if (!astNodeRef.getType().equals(NodeType.COMMAND)) {
          astNodeRef.setParent(parentId);
          astNodeRef.setRef(generateRandomId());
        }
      }
    }
    return astNodeRefs;
  }

  public static List<ASTNode> getAllASTNodes(ASTNode astNode) {
    List<ASTNode> nodes = new java.util.ArrayList<>();
    nodes.add(astNode);
    for (ASTNode node : astNode.getChildren()) {
      nodes.addAll(getAllASTNodes(node));
    }
    return nodes;
  }

  public static ASTNodeRef astToASTNodeRef(ASTNode astNodes) {
    return ASTNodeRef.builder()
        .type(astNodes.getType())
        .value(astNodes.getValue())
        .key(astNodes.getKey())
        .build();
  }

  public static ASTNode astNodeRefToAST(ASTNodeRef astNodeRef) {

    ASTNode astNode = new ASTNode(astNodeRef.getType(), astNodeRef.getValue());
    astNode.setKey(astNodeRef.getKey());
    return astNode;
  }

  private static String generateRandomId() {
    return java.util.UUID.randomUUID().toString();
  }
}
