package termfrequency;


public class TermFrequency {
	private static final int MINIMUM_WORD_LENGTH = 2;
	private Source source;
	private StopWords stopWords;

	public TermFrequency(Source source, StopWords stopWords) {
		this.source = source;
		this.stopWords = stopWords;
	}

	public void termFrequency(Distribution frequencyTable) {
		for (Word word: source) {
			recordWord(frequencyTable, word);
		}
	}

	private void recordWord(Distribution frequencyTable, Word word) {
		if (!isIgnored(word)) {
			frequencyTable.add(word);
		}
	}

	private boolean isIgnored(Word word) {
		return isStopWord(word) || isTiny(word);
	}

	private boolean isTiny(Word word) {
		return word.length() < MINIMUM_WORD_LENGTH;
	}

	private boolean isStopWord(Word word) {
		return stopWords.isStopWord(word);
	}
	
}
