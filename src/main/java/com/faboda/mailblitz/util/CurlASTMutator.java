package com.faboda.mailblitz.util;

import com.faboda.curl.ast.ASTNode;
import com.faboda.curl.ast.CurlASTBuilder;
import com.faboda.curl.ast.NodeType;
import com.faboda.curl.tokenizer.CurlTokenizer;
import com.faboda.curl.tokenizer.StandardCurlTokenizer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurlASTMutator {

  public static ASTNode curlToAST(String curlString) {
    CurlTokenizer curlTokenizer = new StandardCurlTokenizer(curlString);
    List<String> tokens = curlTokenizer.getTokens();
      return CurlASTBuilder.buildAST(tokens);
  }

  public static ASTNode setASTData(ASTNode astNode, String dataKey, String dataValue) {
    List<ASTNode> dataNodes =
        new java.util.ArrayList<>(
            astNode.getChildren().stream()
                .filter(astNode1 -> astNode1.getType().equals(NodeType.DATA))
                .toList());
    ASTNode targetNode =
        dataNodes.stream().filter(node -> node.getKey().equals(dataKey)).findFirst().get();
    int targetIndex = dataNodes.indexOf(targetNode);
    targetNode.setValue(dataValue);
    dataNodes.set(targetIndex, targetNode);
    astNode.resetChildren();
    astNode.addChildren(dataNodes);
    return astNode;
  }
}
