package com.llm.ollama.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class RagController {

    private final VectorStore vectorStore;

    @Autowired
    public RagController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/api/embedding")
    public void embed(@RequestParam(value = "message", defaultValue = "test message") String message) {
        vectorStore.add(List.of(new Document(message)));
    }

    @GetMapping("/api/similarity-search")
    public List<String> similaritySearch(@RequestParam(value = "message") String message) {
        var searchRequest = SearchRequest.builder()
                .query(message)
                .similarityThreshold(0.8)
                .topK(5)
                .build();
        var result = vectorStore.similaritySearch(searchRequest);

        return Optional.ofNullable(result).orElse(Collections.emptyList())
                .stream()
                .map(Document::getText)
                .toList();
    }

}
