# Salt generator

This is a simple Spring Boot application running with Maven, that generates random salt values as per
https://docs.spring.io/spring-security/site/docs/4.0.x/reference/html/crypto.html#spring-security-crypto-encryption-bytes.

You can run it from the shell like this:

```shell
 ./mvnw spring-boot:run -Dspring-boot.run.arguments=<number>
```

Where `<number>` is the number of random salt values you want to generate, by default this number is `6`.

Example

```shell
 ./mvnw spring-boot:run -Dspring-boot.run.arguments=10
```

The application will start, will print the values and will stop. It will look like this

