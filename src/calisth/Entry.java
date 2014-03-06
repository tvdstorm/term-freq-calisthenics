package calisth;


public class Entry implements Comparable<Entry>{
	private Word word;
	private Frequency frequency;

	public Entry(Word word, Frequency frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	public String toString() {
		return word + " - " + frequency;
	}

	@Override
	public int compareTo(Entry o) {
		return frequency.compareTo(o.frequency);
	}
	
	public boolean hasWord(Word w) {
		return word.equals(w);
	}
	
	public boolean hasFrequency(Frequency f) {
		return frequency.equals(f);
	}
	
}
