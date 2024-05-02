package com.faboda.mailblitz.service;

import com.faboda.mailblitz.web.requests.CurlRequest;
import com.faboda.mailblitz.web.response.CurlResponse;

public interface SubscriptionService {

  CurlResponse addMail(CurlRequest curlRequest);
}
