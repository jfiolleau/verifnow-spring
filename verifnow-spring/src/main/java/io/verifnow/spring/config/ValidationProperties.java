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
package io.verifnow.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "verifnow.api")
public class ValidationProperties {

  private String baseUrl = "https://api.verifnow.com";
  private int timeoutMs = 1500;
  private String apiKey;
  private boolean cacheEnabled = true;
  private long cacheTtlSeconds = 60;
  private boolean failOnError = false;

  public String getBaseUrl() {
    return baseUrl;
  }
  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public int getTimeoutMs() {
    return timeoutMs;
  }

  public void setTimeoutMs(int timeoutMs) {
    this.timeoutMs = timeoutMs;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public boolean isCacheEnabled() {
    return cacheEnabled;
  }

  public void setCacheEnabled(boolean cacheEnabled) {
    this.cacheEnabled = cacheEnabled;
  }

  public long getCacheTtlSeconds() {
    return cacheTtlSeconds;
  }

  public void setCacheTtlSeconds(long cacheTtlSeconds) {
    this.cacheTtlSeconds = cacheTtlSeconds;
  }

  public boolean isFailOnError() {
    return failOnError;
  }

  public void setFailOnError(boolean failOnError) {
    this.failOnError = failOnError;
  }
}
