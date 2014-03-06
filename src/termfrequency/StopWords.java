package termfrequency;

import java.util.HashSet;
import java.util.Set;

public class StopWords {
	private final Set<Word> stopWords;

	public StopWords(String stopWordsText) {
		this.stopWords = new HashSet<Word>();
		initialize(stopWords, stopWordsText);
	}

	private static void initialize(Set<Word> stopWords, String text) {
		for (String word: text.split(",")) {
			stopWords.add(new Word(word));
		}
	}
	
	public boolean isStopWord(Word word) {
		return stopWords.contains(word);
	}
	
}
