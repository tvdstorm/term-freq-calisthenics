package termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		Entry prev = null;
		for (Entry entry: ranking) {
			assertTrue(prev == null || prev.compareTo(entry) > 0);
			prev = entry;
		}
	}
	
	@Test
	public void rankingsAreNotLargerThanAskedFor() {
		int askForSmallRanking = 2;
		Ranking ranking = new Ranking(askForSmallRanking);
		ranking.add(new Word("a"), new Frequency(2));
		ranking.add(new Word("b"), new Frequency(1));
		ranking.add(new Word("c"), new Frequency(0));
		assertEquals(askForSmallRanking, ranking.size());
	}
	
	@Test
	public void rankingsAreNotLargeThanTheirSize() {
		int askForBigRanking = 10;
		Ranking ranking = new Ranking(askForBigRanking);
		ranking.add(new Word("a"), new Frequency(2));
		ranking.add(new Word("b"), new Frequency(1));
		ranking.add(new Word("c"), new Frequency(0));
		assertEquals(3, ranking.size());
	}
}
