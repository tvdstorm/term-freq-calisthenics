package calisth;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Ranking implements Iterable<Entry> {
	private int maximumSize;
	private List<Entry> entries;

	public Ranking(int maximumSize) {
		assert maximumSize >= 0;
		this.maximumSize = maximumSize;
		this.entries = new ArrayList<>();
	}
	
	public void add(Word word, Frequency frequency) {
		entries.add(new Entry(word, frequency));
	}
	
	public int size() {
		return actualSize(entries);
	}
	
	@Override
	public Iterator<Entry> iterator() {
		return subView().iterator();
	}

	private List<Entry> subView() {
		List<Entry> list = asDescendingList();
		return list.subList(0, actualSize(list));
	}

	private int actualSize(List<Entry> list) {
		return min(list.size(), maximumSize);
	}

	private List<Entry> asDescendingList() {
		List<Entry> list = new ArrayList<>(entries);
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

}
