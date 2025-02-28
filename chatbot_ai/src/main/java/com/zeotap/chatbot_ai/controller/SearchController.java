package com.zeotap.chatbot_ai.controller;

import com.zeotap.chatbot_ai.service.LuceneSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final LuceneSearchService searchService;

    public SearchController(LuceneSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<String> search(@RequestParam String query) throws Exception {
        return searchService.searchDocuments(query);
    }
}
