package com.faboda.mailblitz.web.requests;

import lombok.Data;

@Data
public class CurlRequest {
  private String curl;
  private String valueVariable;
}
