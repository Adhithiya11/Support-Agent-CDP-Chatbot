package com.zeotap.chatbot_ai;

import com.zeotap.chatbot_ai.service.LuceneIndexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IndexInitializer implements CommandLineRunner {
    private final LuceneIndexService luceneIndexService;

    public IndexInitializer(LuceneIndexService luceneIndexService) {
        this.luceneIndexService = luceneIndexService;
    }

    @Override
    public void run(String... args) throws Exception {
        luceneIndexService.addDocument(
                "Setting up a source in Segment",
                "To set up a source in Segment, navigate to the Sources tab and click 'Add Source'. Choose the appropriate integration and follow the setup instructions.",
                "Segment"
        );

        luceneIndexService.addDocument(
                "Creating a user profile in mParticle",
                "To create a user profile in mParticle, go to the Profiles section and click 'New Profile'. Enter user details and save.",
                "mParticle"
        );

        luceneIndexService.addDocument(
                "Building an audience in Lytics",
                "To build an audience in Lytics, open the Segments tab and define your segment criteria.",
                "Lytics"
        );

        luceneIndexService.addDocument(
                "Integrating data with Zeotap",
                "To integrate data with Zeotap, use the Data Connector API for seamless data transfer.",
                "Zeotap"
        );

        System.out.println("Sample CDP documentation indexed successfully!");
    }
}
