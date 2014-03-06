package calisth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	private static final int NUM_OF_ENTRIES = 25;
	
	// yes, I know.
	private static final String STOP_WORDS = "/Users/tvdstorm/Documents/exercises-in-programming-style/stop_words.txt";

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("use: termfreq <textfile>");
			System.exit(1);
		}
		runTermFrequency(args[0], STOP_WORDS);
	}

	private static void runTermFrequency(String source, String stopWords) throws IOException {
		String sourceText = readFile(source);
		String stopWordsText = readFile(STOP_WORDS);
		
		TermFrequency termFrequency = new TermFrequency(new Source(sourceText), new StopWords(stopWordsText));
		Distribution frequencyTable = new Distribution();
		termFrequency.termFrequency(frequencyTable);
		Ranking ranking = new Ranking(NUM_OF_ENTRIES);
		frequencyTable.rank(ranking);
		for (Entry wp: ranking) {
			System.out.println(wp);
		}
	}

	private static String readFile(String path) throws IOException {
		File file = new File(path);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        return sb.toString();
	    }
	}
}
