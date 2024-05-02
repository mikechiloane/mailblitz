package com.faboda.mailblitz.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurlResponse {
  private String id;
  private String valueVariable;
}
