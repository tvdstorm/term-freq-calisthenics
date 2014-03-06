package termfrequency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Source implements Iterable<Word> {
	private static final String NON_ALPHA_NUMERIC_CHAR = "[^a-zA-Z0-9]";
	private static final String WHITESPACE = "[\\ \\t\\n]+";
	private final List<Word> words;

	public Source(String text) {
		this.words = new ArrayList<Word>();
		initialize(words, text);
	}
	
	private static void initialize(List<Word> words, String text) {
		for (String s : parse(normalize(text))) {
			words.add(new Word(s));
		}
	}

	private static String[] parse(String text) {
		return text.split(WHITESPACE);
	}

	private static String normalize(String s) {
		return filterNonAlphaNumerics(s).toLowerCase();
	}

	private static String filterNonAlphaNumerics(String s) {
		return s.replaceAll(NON_ALPHA_NUMERIC_CHAR, " ");
	}

	@Override
	public Iterator<Word> iterator() {
		return words.iterator();
	}

}
