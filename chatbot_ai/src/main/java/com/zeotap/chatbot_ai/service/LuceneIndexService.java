package com.zeotap.chatbot_ai.service;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class LuceneIndexService {
    private static final String INDEX_DIR = "lucene_index";
    private final IndexWriter indexWriter;

    // Constructor initializes the IndexWriter only once
    public LuceneIndexService() throws IOException {
        Directory indexDirectory = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        this.indexWriter = new IndexWriter(indexDirectory, config);
    }

    /**
     * Indexes a new document.
     *
     * @param docId   The unique identifier for the document.
     * @param content The content to be indexed.
     */
    public void indexDocument(String docId, String content) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("id", docId, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));

        indexWriter.addDocument(doc);
        indexWriter.commit(); // Commit changes to persist data
    }

    /**
     * Adds a document to the Lucene index with additional metadata.
     *
     * @param title   The title of the document.
     * @param content The actual content of the document.
     * @param cdp     The name of the CDP (Segment, mParticle, Lytics, Zeotap).
     */
    public void addDocument(String title, String content, String cdp) throws IOException {
        Document document = new Document();
        document.add(new StringField("title", title, Field.Store.YES));
        document.add(new TextField("content", content, Field.Store.YES));
        document.add(new StringField("cdp", cdp, Field.Store.YES));

        indexWriter.addDocument(document);
        indexWriter.commit();
    }

    /**
     * Closes the IndexWriter when the application shuts down to release file locks.
     */
    @PreDestroy
    public void closeIndexWriter() {
        try {
            if (indexWriter != null) {
                indexWriter.close();
                System.out.println("Lucene IndexWriter closed successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
