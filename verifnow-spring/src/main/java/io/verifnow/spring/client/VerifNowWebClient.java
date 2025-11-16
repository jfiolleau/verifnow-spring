/**
 * Copyright (c) 2023-2025 Jerome Fiolleau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.verifnow.spring.client;

import io.verifnow.core.client.VerifNowClient;
import io.verifnow.core.client.ValidationResult;
import io.verifnow.spring.config.ValidationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class VerifNowWebClient implements VerifNowClient {

  private final WebClient webClient;
  private final ValidationProperties props;

  public VerifNowWebClient(WebClient.Builder builder, ValidationProperties props) {
    this.props = props;
    WebClient.Builder b = builder.baseUrl(props.getBaseUrl());
    if (props.getApiKey() != null) {
      b.defaultHeader("X-API-KEY", props.getApiKey());
    }
    this.webClient = b.build();
  }

  @Override
  public ValidationResult validate(String rule, String value) {
    try {
      Mono<ValidationResult> mono = webClient.post()
          .uri(uriBuilder -> uriBuilder.path("/api/v1/validate/{rule}").build(rule))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
          .bodyValue(new RequestPayload(value))
          .retrieve()
          .bodyToMono(ValidationResult.class)
          .timeout(Duration.ofMillis(props.getTimeoutMs()));

      // Blocking because ConstraintValidator.isValid is synchronous
      return mono.block();
    } catch (WebClientResponseException wex) {
      throw wex;
    } catch (Exception ex) {
      if (props.isFailOnError()) {
        // fail closed: throw runtime to fail validation flow
        throw new RuntimeException("VerifNow API error", ex);
      }
      // fail open: return permissive result
      ValidationResult r = new ValidationResult();
      r.setValid(true);
      r.setMessage("fallback-permit");
      return r;
    }
  }

  @Override
  public CompletableFuture<ValidationResult> validateAsync(String rule, String value) {
    Mono<ValidationResult> mono = webClient.post()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/{rule}").build(rule))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(new RequestPayload(value))
        .retrieve()
        .bodyToMono(ValidationResult.class)
        .timeout(Duration.ofMillis(props.getTimeoutMs()));

    return mono.toFuture();
  }

  private static class RequestPayload {
    private String value;

    public RequestPayload(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
