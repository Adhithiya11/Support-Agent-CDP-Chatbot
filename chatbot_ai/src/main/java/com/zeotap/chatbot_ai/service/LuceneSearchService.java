package com.zeotap.chatbot_ai.service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

@Service
public class LuceneSearchService {
	private static final String INDEX_DIR = "lucene_index"; // Directory where index is stored

	public List<String> searchDocuments(String queryText, int maxResults) throws Exception {
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
		Query query = queryParser.parse(queryText);

		TopDocs topDocs = indexSearcher.search(query, maxResults);
		List<String> results = new ArrayList<>();

		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			results.add(doc.get("title") + ": " + doc.get("content"));
		}

		indexReader.close();
		directory.close();
		return results;
	}

	public List<String> searchDocuments(String userQuery) throws Exception {
		// Open the directory where the index is stored
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIR));
		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		// Create a query parser for the "content" field
		QueryParser parser = new QueryParser("content", new StandardAnalyzer());
		Query query = parser.parse(userQuery);

		// Execute search and retrieve top 10 results
		TopDocs topDocs = searcher.search(query, 10);

		// Extract results
		List<String> results = new ArrayList<>();
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			results.add("Title: " + doc.get("title") + " | CDP: " + doc.get("cdp"));
		}

		reader.close();
		return results;
	}
}
