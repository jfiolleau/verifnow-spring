/**
 * Copyright (c) 2025-2025 Jerome Fiolleau
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
package io.verifnow.core.client;

public class ValidationResult {
  private boolean valid;
  private String normalizedValue;
  private String originalValue;
  private String message;

  public ValidationResult() {}

  public ValidationResult(boolean valid, String originalValue, String message) {
    this.valid = valid;
    this.originalValue = originalValue;
    this.message = message;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getNormalizedValue() {
    return normalizedValue;
  }

  public void setNormalizedValue(String normalizedValue) {
    this.normalizedValue = normalizedValue;
  }

  public String getOriginalValue() {
    return originalValue;
  }

  public void setOriginalValue(String originalValue) {
    this.originalValue = originalValue;
  }
}
