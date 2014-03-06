package termfrequency;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
	@Test
	public void stopWordsAreNotCounted() {
		StopWords sw = new StopWords("the");
		Source src = new Source("the");
		TermFrequency tf = new TermFrequency(src, sw);
		Distribution ft = new Distribution();
		tf.termFrequency(ft);
		Ranking ranking = new Ranking(1);
		ft.rank(ranking);
		assertEquals(0, ranking.size()); 
	}
	
	@Test
	public void tinyWordsAreNotCounted() {
		StopWords sw = new StopWords("");
		Source src = new Source("a b c");
		TermFrequency tf = new TermFrequency(src, sw);
		Distribution ft = new Distribution();
		tf.termFrequency(ft);
		Ranking ranking = new Ranking(1);
		ft.rank(ranking);
		assertEquals(0, ranking.size()); 
	}
	
	@Test
	public void wordsAreNormalizedWrtCase() {
		Source src = new Source("the THE The THe ThE");
		Word the = new Word("the");
		for (Word w: src) {
			assertEquals(the, w);
		}
	}
	
	@Test
	public void nonAlphaNumericsAreIgnored() {
		Source src = new Source("the, THE-The; %THe% ThE$$$");
		Word the = new Word("the");
		int numOfWords = 0;
		for (Word w: src) {
			numOfWords++;
			assertEquals(the, w);
		}
		assertEquals(5, numOfWords);
	}
	
	@Test
	public void rankingIsInDescendingOrder() {
		Ranking ranking = new Ranking(2);
		ranking.add(new Word("a"), new Frequency(2));
		ranking.add(new Word("b"), new Frequency(1));
		List<Entry> pairs = new ArrayList<>();
		for (Entry pair: ranking) {
			pairs.add(pair);
		}
		Entry a = pairs.get(0);
		Entry b = pairs.get(1);
		assertTrue(a.hasWord(new Word("a")));
		assertTrue(b.hasWord(new Word("b")));
		assertTrue(a.compareTo(b) > 0);
	}
	
	@Test
	public void rankingsAreNotLargerThanAskedFor() {
		// todo;
	}
}
