package calisth;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Distribution  {
	private Map<Word,Frequency> table;
	
	public Distribution() {
		this.table = new HashMap<Word, Frequency>();
	}

	public void add(Word word) {
		initializeWordFrequencyIfNeeded(word);
		incrementWordFrequency(word);
	}
	
	private void incrementWordFrequency(Word word) {
		table.get(word).increment();
	}

	private void initializeWordFrequencyIfNeeded(Word word) {
		if (!table.containsKey(word)) {
			table.put(word, new Frequency());
		}
	}

	public void rank(Ranking ranking) {
		for (Entry<Word, Frequency> entry: table.entrySet()) {
			Word word = entry.getKey();
			Frequency frequency = entry.getValue();
			ranking.add(word, frequency);
		}
	}

}
