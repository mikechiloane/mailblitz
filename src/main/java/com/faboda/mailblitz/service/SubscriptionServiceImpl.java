package com.faboda.mailblitz.service;

import com.faboda.curl.ast.ASTNode;
import com.faboda.http.ASTHttpClientWrapper;
import com.faboda.mailblitz.model.ASTNodeRef;
import com.faboda.mailblitz.repository.ASTNodeRefRepository;
import com.faboda.mailblitz.repository.CurlASTRequestRepository;
import com.faboda.mailblitz.util.CurlASTMutator;
import com.faboda.mailblitz.web.requests.CurlRequest;
import com.faboda.mailblitz.web.response.CurlResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final CurlASTRequestRepository curlASTRequestRepository;
  private final ASTNodeRefRepository astNodeRefRepository;

  @Override
  public CurlResponse addMail(CurlRequest curlRequest) {
    ASTNode astNode = CurlASTMutator.curlToAST(curlRequest.getCurl());
    List<ASTNodeRef> astNodeRefs = CurlASTMutator.getAllASTNodeRefs(astNode);
    astNodeRefRepository.saveAll(astNodeRefs);
    return null;
  }

  @Override
  public void subscribeUser(String email) {
    System.out.println("Subscribing user");
    Optional<ASTNodeRef> curlASTRequest = astNodeRefRepository.findById("6634e409c2578734eb310a63");
    if (curlASTRequest.isPresent()) {
      System.out.println("Curl AST Request found");
      ASTNodeRef astNodeRef = curlASTRequest.get();
      ASTNode astNode = buildASTNodeForRef(astNodeRef.getRef());
      ASTHttpClientWrapper astHttpClientWrapper = new ASTHttpClientWrapper();
      try {
        String response = astHttpClientWrapper.send(astNode);
        System.out.println(response);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private ASTNode buildASTNodeForRef(String ref) {
    Optional<ASTNodeRef> refOptional = astNodeRefRepository.findByRef(ref);
    List<ASTNodeRef> astNodeRefs = astNodeRefRepository.findByParent(ref);
    if (refOptional.isPresent()) {
      ASTNode parent = CurlASTMutator.astNodeRefToAST(refOptional.get());
      parent.setValue("curl");
      astNodeRefs.forEach(
          nodeRef -> {
            ASTNode child = CurlASTMutator.astNodeRefToAST(nodeRef);
            child.setParent(parent);
            parent.addChild(child);
          });
      return parent;

    }
    return null;
  }
}
