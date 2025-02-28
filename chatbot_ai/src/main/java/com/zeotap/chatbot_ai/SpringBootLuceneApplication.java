package com.zeotap.chatbot_ai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zeotap.chatbot_ai.service.LuceneIndexService;

import java.io.IOException;

@SpringBootApplication
public class SpringBootLuceneApplication implements CommandLineRunner {
    private final LuceneIndexService luceneIndexService;

    public SpringBootLuceneApplication(LuceneIndexService luceneIndexService) {
        this.luceneIndexService = luceneIndexService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLuceneApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        // Add sample CDP documentation
        luceneIndexService.indexDocument("1", "How to set up a new source in Segment? Go to the Sources tab and click 'Add Source'.");
        luceneIndexService.indexDocument("2", "How to create a user profile in mParticle? Navigate to 'Profiles' and click 'New Profile'.");
        luceneIndexService.indexDocument("3", "How to build an audience in Lytics? Open the Segments tab and define your conditions.");
        luceneIndexService.indexDocument("4", "How to integrate data with Zeotap? Use the Data Connector API for seamless integration.");
        
        System.out.println("Lucene Indexing Completed.");
    }
}

