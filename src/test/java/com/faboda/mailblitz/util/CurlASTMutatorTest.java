package com.faboda.mailblitz.util;

import static org.junit.jupiter.api.Assertions.*;

import com.faboda.curl.MockASTService;
import com.faboda.curl.ast.ASTNode;
import org.junit.jupiter.api.Test;

public class CurlASTMutatorTest {
  @Test
  public void testCurlMutator() {
    ASTNode astNode = MockASTService.buildAST();
    CurlASTMutator.setASTData(astNode, "title", "Test Title");
    ASTNode titleDataNode =
        astNode.getChildren().stream()
            .filter(node -> node.getKey().equals("title"))
            .findFirst()
            .get();
    assertEquals("Test Title", titleDataNode.getValue());
    CurlASTMutator.setASTData(astNode, "title", "New Title");
    titleDataNode =
        astNode.getChildren().stream()
            .filter(node -> node.getKey().equals("title"))
            .findFirst()
            .get();
    assertNotEquals("Test Title", titleDataNode.getValue());
  }
}
