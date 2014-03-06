package calisth;

public class Word {
	private final String value;
	
	public Word(String value) {
		assert !value.isEmpty();
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Word)) {
			return false;
		}
		return value.equals(((Word)obj).value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public String toString() {
		return value;
	}

	public int length() {
		return value.length();
	}

}
