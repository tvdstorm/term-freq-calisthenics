package termfrequency;

public class Frequency implements Comparable<Frequency> {
	private int value;
	
	public Frequency() {
		this(0);
	}
	
	public Frequency(int value) {
		assert value >= 0;
		this.value = value;
	}
	
	public void increment() {
		value++;
	}
	
	@Override
	public String toString() {
		return new Integer(value).toString();
	}
	
	public int compareTo(Frequency f) {
		return Integer.compare(value, f.value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Frequency)) {
			return false;
		}
		return value == ((Frequency)obj).value;
	}

}
