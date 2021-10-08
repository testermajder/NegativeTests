package com.projectname.api.client.utils;

import java.io.Serializable;

public class ClientCredentials implements Serializable {

  private final String clientId;
  private final String clientSecret;

  public ClientCredentials(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
