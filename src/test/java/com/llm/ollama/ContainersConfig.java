package com.llm.ollama;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

    private static final String POSTGRES = "postgres";
    private static final int OLLAMA_PORT = 11434;

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("pgvector/pgvector:pg17")
                .withUsername(POSTGRES)
                .withPassword(POSTGRES)
                .withDatabaseName(POSTGRES)
                .withInitScript("schema.sql")
                .withReuse(true);
    }


    @Bean
    public GenericContainer<?> ollamaContainer() {
        return new GenericContainer("alpine/ollama:latest")
                .withExposedPorts(OLLAMA_PORT)
                .withReuse(true);
    }

    @Bean
    public DynamicPropertyRegistrar ollamaDynamicProperties(GenericContainer<?> ollamaContainer) {
        return registry -> registry.add("spring.ai.ollama.base-url",
                () -> "http://localhost:" + ollamaContainer.getMappedPort(OLLAMA_PORT));
    }

}
