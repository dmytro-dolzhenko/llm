package com.llm.ollama;

import org.springframework.boot.SpringApplication;

public class TestApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(OllamaApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }

}
