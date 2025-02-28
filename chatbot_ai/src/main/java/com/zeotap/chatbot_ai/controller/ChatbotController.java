package com.zeotap.chatbot_ai.controller;

import com.zeotap.chatbot_ai.service.LuceneSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private LuceneSearchService luceneSearchService;

    @GetMapping("/search")
    public List<String> search(@RequestParam String query) {
        try {
            return luceneSearchService.searchDocuments(query, 5); // Return top 5 results
        } catch (Exception e) {
            return List.of("Error processing query: " + e.getMessage());
        }
    }
}
