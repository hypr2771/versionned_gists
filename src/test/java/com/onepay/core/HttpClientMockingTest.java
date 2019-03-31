package com.onepay.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HttpClientMockingTest {

  private OnePayHttpClient client;

  @Test
  public void testMock() throws ExecutionException, InterruptedException {
    client = Mockito.mock(OnePayHttpClient.class);
    var httpClient = Mockito.mock(HttpClient.class);

    var future = CompletableFuture.completedFuture("Hello");

    Mockito.when(httpClient.sendAsync(nullable(HttpRequest.class), nullable(BodyHandler.class))).thenReturn(future);

    Mockito.when(client.get()).thenReturn(httpClient);

    var result = client.get().sendAsync(null, null);

    assertEquals(result.get(), "Hello");
  }

}
