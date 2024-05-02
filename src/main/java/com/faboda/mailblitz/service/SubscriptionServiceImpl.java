package com.faboda.mailblitz.service;

import com.faboda.curl.ast.ASTNode;
import com.faboda.mailblitz.model.CurlASTRequest;
import com.faboda.mailblitz.repository.CurlASTRequestRepository;
import com.faboda.mailblitz.util.CurlASTMutator;
import com.faboda.mailblitz.web.requests.CurlRequest;
import com.faboda.mailblitz.web.response.CurlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final CurlASTRequestRepository curlASTRequestRepository;

  @Override
  public CurlResponse addMail(CurlRequest curlRequest) {
    ASTNode astNode = CurlASTMutator.curlToAST(curlRequest.getCurl());
    CurlASTRequest curlASTRequest =
        CurlASTRequest.builder()
            .astNode(astNode)
            .dataVariable(curlRequest.getValueVariable())
            .build();
    CurlASTRequest createdNode = curlASTRequestRepository.save(curlASTRequest);
    return CurlResponse.builder()
        .id(createdNode.getId())
        .valueVariable(createdNode.getDataVariable())
        .build();
  }
}
