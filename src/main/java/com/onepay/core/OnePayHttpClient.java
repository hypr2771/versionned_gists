package com.onepay.core;

import java.net.http.HttpClient;

public class OnePayHttpClient {

  public HttpClient get(){
    return HttpClient.newHttpClient();
  }

}
