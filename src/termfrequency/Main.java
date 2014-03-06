package termfrequency;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
	private static final int NUM_OF_ENTRIES = 25;
	private static final String STOP_WORDS = "termfrequency/stop_words.txt";

	public static void main(String[] args) {
		String sourcePath = parseArgs(args);
		try {
			run(sourcePath, System.out);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private static void run(String sourcePath, PrintStream output) throws IOException {
		StopWords stopWords = new StopWords(loadStopWords());
		Source source = new Source(readFile(sourcePath));
		Ranking ranking = termFrequency(source, stopWords);
		for (Entry entry: ranking) {
			output.println(entry);
		}
	}
	
	private static String parseArgs(String[] args) {
		if (args.length < 1) {
			System.err.println("use: termfreq <textfile>");
			System.exit(1);
		}
		return args[0];
	}

	private static Ranking termFrequency(Source source, StopWords stopWords) {
		TermFrequency termFrequency = new TermFrequency(source, stopWords);
		Distribution frequencyTable = new Distribution();
		termFrequency.termFrequency(frequencyTable);
		Ranking ranking = new Ranking(NUM_OF_ENTRIES);
		frequencyTable.rank(ranking);
		return ranking;
	}

	private static String loadStopWords() throws IOException {
		ClassLoader loader = Main.class.getClassLoader();
		InputStream stopWords = loader.getResourceAsStream(STOP_WORDS);
		return readStream(stopWords);
	}
	
	private static String readFile(String path) throws IOException {
		return readStream(new FileInputStream(path));
	}
	private static String readStream(InputStream stopWords) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stopWords))) {
	        StringBuilder sb = new StringBuilder();
	        readLines(br, sb);
	        return sb.toString();
	    }
	}

	private static void readLines(BufferedReader br, StringBuilder sb) throws IOException {
		String line = br.readLine();
		while (line != null) {
		    sb.append(line);
		    sb.append(System.lineSeparator());
		    line = br.readLine();
		}
	}
	
}
