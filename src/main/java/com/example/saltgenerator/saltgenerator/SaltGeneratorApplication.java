package com.example.saltgenerator.saltgenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

@SpringBootApplication
public class SaltGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaltGeneratorApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return arguments -> {
            var numberOfSaltsToGenerate = Optional.ofNullable(arguments)
                                                  .filter(args -> args.length > 0)
                                                  .map(args -> args[0])
                                                  .map(SaltGeneratorApplication::toInt)
                                                  .orElse(6);
            var info = """
                       === Generating %s salt values ===
                                              
                       %s
                                              
                       ============== Done ==============
                       """
                    .formatted(numberOfSaltsToGenerate, generateNSaltValues(numberOfSaltsToGenerate));

            System.out.println(info);
        };
    }

    @Nullable
    private static Integer toInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (NullPointerException | NumberFormatException e) {
            return null;
        }
    }

    private static String generateNSaltValues(int n) {
        return IntStream.range(0, n)
                        .mapToObj(i -> KeyGenerators.string().generateKey())
                        .collect(joining(lineSeparator()));
    }
}
