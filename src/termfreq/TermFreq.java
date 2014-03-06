package termfreq;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TermFreq {

	private final Set<String> stopWords;
	private final String text;
	private final int numOfEntriesToPrint;

	public TermFreq(Set<String> stopWords, String text, int numOfEntriesToPrint) {
		assert numOfEntriesToPrint >= 0;
		this.stopWords = stopWords;
		this.text = text;
		this.numOfEntriesToPrint = numOfEntriesToPrint;
	}

	public void run(PrintWriter writer) {
		Map<String, Integer> freq = new HashMap<>();
		buildFrequencyTable(freq);
		printMostFrequentWords(freq, writer);
	}

	private void buildFrequencyTable(Map<String, Integer> freq) {
		List<String> words = new ArrayList<>();
		parseWordsInText(words);
		for (String w : words) {
			recordWord(w, freq);
		}
	}

	private void recordWord(String word, Map<String, Integer> freq) {
		assert !word.isEmpty();
		
		if (isStopWord(word)) {
			return;
		}
		initFreqForWordIfNeeded(word, freq);
		incrementFreqForWord(word, freq);
	}

	private boolean isStopWord(String word) {
		return stopWords.contains(word);
	}

	private void incrementFreqForWord(String word, Map<String, Integer> freq) {
		assert freq.containsKey(word);
		freq.put(word, freq.get(word) + 1);
	}

	private void initFreqForWordIfNeeded(String word, Map<String, Integer> freq) {
		if (!freq.containsKey(word)) {
			freq.put(word, 0);
		}
	}

	private void parseWordsInText(List<String> list) {
		for (String s : splitTextOnWhitespace()) {
			addNormalizedWord(list, s);
		}
	}

	private String[] splitTextOnWhitespace() {
		return text.split("[\\ \\t\\n]");
	}

	private void addNormalizedWord(List<String> list, String s) {
		String n = normalize(s);
		if (!n.equals("")) {
			list.add(n);
		}
	}

	private void printMostFrequentWords(Map<String, Integer> freq, PrintWriter writer) {
		List<Entry<String, Integer>> l = new ArrayList<>(freq.entrySet());
		reverseSortOnFrequency(l);
		printEntries(writer, l);
	}

	private void printEntries(PrintWriter writer, List<Entry<String, Integer>> l) {
		for (int i = 0; i < numOfEntriesToPrint && i < l.size(); i++) {
			Entry<String, Integer> e = l.get(i);
			writer.println(e.getKey() + " - " + e.getValue());
		}
	}

	private static void reverseSortOnFrequency(List<Entry<String, Integer>> l) {
		Collections.sort(l, reverseValueComparator());
	}

	private static Comparator<Entry<String, Integer>> reverseValueComparator() {
		return new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return Integer.compare(o2.getValue(), o1.getValue());
			}
		};
	}

	private static String normalize(String s) {
		return filterNonAlphaNumerics(s).toLowerCase();
	}

	private static String filterNonAlphaNumerics(String s) {
		return s.replaceAll("[^a-zA-Z]", "");
	}

}
