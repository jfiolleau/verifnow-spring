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
package io.verifnow.spring.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import io.verifnow.spring.validators.VerifNowPhoneValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = VerifNowPhoneValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface VerifNowPhone {
  String message() default "{validation.verifnow.phone.invalid}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  String profile() default "";
  boolean allowNull() default true;
}
