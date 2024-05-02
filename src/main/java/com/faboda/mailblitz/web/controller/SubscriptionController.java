package com.faboda.mailblitz.web.controller;

import com.faboda.mailblitz.service.SubscriptionService;
import com.faboda.mailblitz.web.requests.CurlRequest;
import com.faboda.mailblitz.web.requests.SubscriptionRequest;
import com.faboda.mailblitz.web.response.CurlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @PostMapping
  public CurlResponse astNode(@RequestBody CurlRequest curlRequest) {
    return subscriptionService.addMail(curlRequest);
  }

  @PostMapping("/subscribe")
  public void subscribeUser(@RequestBody SubscriptionRequest subscriptionRequest) {
    subscriptionService.subscribeUser(subscriptionRequest.getEmail());
  }
}
