package com.zeotap.chatbot_ai.utils;

public class QuestionNormalizer {

	// Common variations that should be standardized
	private static final String[][] NORMALIZATION_RULES = { { "(?i)how can I", "how to" },
			{ "(?i)can you tell me how to", "how to" }, { "(?i)I need to", "how to" },
			{ "(?i)what steps are required for", "how to" }, { "(?i)please", "" } // Remove "please"
	};

	public static String normalizeQuestion(String question) {
		if (question == null || question.trim().isEmpty()) {
			return "";
		}

		// Trim and remove extra spaces
		question = question.trim().replaceAll("\\s+", " ");

		// Apply normalization rules
		for (String[] rule : NORMALIZATION_RULES) {
			question = question.replaceAll(rule[0], rule[1]);
		}

		// Remove leading/trailing punctuation
		question = question.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

		return question;
	}

	// Test the normalizer
	public static void main(String[] args) {
		String[] testQuestions = { "How do I set up a new source in Segment?",
				"Can you tell me how to create a source in Segment?",
				"I need to add a source in Segment. How can I do that?",
				"What steps are required for setting up a source in Segment?",
				"segment new source setup instructions please" };

		for (String question : testQuestions) {
			System.out.println("Original: " + question);
			System.out.println("Normalized: " + normalizeQuestion(question));
			System.out.println("-------------------");
		}
	}
}
