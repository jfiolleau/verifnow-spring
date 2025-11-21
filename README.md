# VerifNow Java SDK

[![Build](https://github.com/jfiolleau/verifnow-spring/actions/workflows/ci.yml/badge.svg)](https://github.com/jfiolleau/verifnow-spring/actions/workflows/ci.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.verifnow/verifnow-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.verifnow/verifnow-parent)
[![License: Apache-2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)


SDK Java officiel pour interagir avec l'API **VerifNow** : validation de données, vérification d'identité, et services de conformité.

## Before you start

This SDK integrates with the VerifNow API. To use it:

1. Create an account and subscribe to a plan.
2. Create one or more API keys (apiKey) in the client app.
3. Configure this API key in your application so the SDK can call VerifNow.

Website and documentation:
- Official site with overview, usage guide, documentation, and link to the client app: https://www.verifnow.io

---

## Configuration (Spring Boot)

This starter auto-configures a Spring WebClient and exposes validation annotations. Properties are configured under the `verifnow.api` prefix.

Example with `application.yml`:

```yaml
verifnow:
  api:
    apiKey: "YOUR_API_KEY"           # required to call the VerifNow API
    # baseUrl: https://api.verifnow.com # default
    # timeoutMs: 1500                  # default
    # cacheEnabled: true               # default
    # cacheTtlSeconds: 60              # default
    # failOnError: false               # default (fail-open)
```

Or with `application.properties`:

```properties
verifnow.api.apiKey=YOUR_API_KEY
# verifnow.api.baseUrl=https://api.verifnow.com
# verifnow.api.timeoutMs=1500
# verifnow.api.cacheEnabled=true
# verifnow.api.cacheTtlSeconds=60
# verifnow.api.failOnError=false
```

Notes:
- `apiKey` is required. Without a valid key, API calls will fail.
- `failOnError=false` is fail-open by default if the API/network errors out. Set to `true` to strictly fail validation on errors.
- `baseUrl` defaults to `https://api.verifnow.com`.

## Modules

- **verifnow-core**: Core validation functionality
- **verifnow-spring**: Spring Framework integration
- **verifnow-spring-boot-starter**: Spring Boot auto-configuration starter


---
## 🚀 Quick Start

### Maven

```xml
<dependency>
    <groupId>io.verifnow</groupId>
    <artifactId>verifnow-spring-boot-starter</artifactId>
    <version>2.0.1</version>
</dependency>
```

### Gradle

```gradle
implementation 'io.verifnow:verifnow-spring-boot-starter:2.0.0'
```

---
## Usage

```java
@Entity
public class User {
    @VerifNowEmail
    private String email;
    
    @VerifNowPhone
    private String phone;
    
    // getters and setters
}
```

Available annotations (examples): `@VerifNowEmail`, `@VerifNowPhone`, `@VerifNowIban`, `@VerifNowVat`, `@VerifNowSsn`, `@VerifNowNas`, `@VerifNowNif`.

## Quick try

A runnable example (minimal Spring Boot app and test) is available in the public repository `jfiolleau/validation-java-example`: [https://github.com/jfiolleau/validation-java-example](https://github.com/jfiolleau/validation-java-example).


Try the integration in a few minutes inside an existing Spring Boot app:

1) Add the dependency (see Quick Start above).

2) Provide your API key (either config file or environment variable). With an environment variable:

```bash
export VERIFNOW_API_APIKEY="YOUR_API_KEY"
```

3) Add a minimal JUnit test to validate with the annotation:

```java
import io.verifnow.spring.annotations.VerifNowEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VerifNowQuickTryTest {
  static class Dto {
    @VerifNowEmail String email;
    Dto(String email) { this.email = email; }
  }

  @Autowired Validator validator;

  @Test
  void validatesEmail() {
    Set<ConstraintViolation<Dto>> violations = validator.validate(new Dto("john.doe@example.com"));
    assertTrue(violations.isEmpty(), () -> "Violations: " + violations);
  }
}
```

Then run tests:

```bash
mvn -q -Dtest=VerifNowQuickTryTest test
```


---

## Building from Source

### Prerequisites

- Java 21 or later
- Maven 3.6 or later

### Build

```bash
mvn clean install
```

### Run Tests

```bash
mvn clean verify
```

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Support

If you have any questions or issues, please open an issue on GitHub.
