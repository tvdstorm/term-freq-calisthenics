package termfreq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TFMain {
	private static final int MAX_NUM_OF_ENTRIES = 25;
	private static final File STOPWORDS = new File("/Users/tvdstorm/CWI/exercises-in-programming-style/stop_words.txt");

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("use: termfreq <textfile>");
			System.exit(1);
		}
		TermFreq tf = new TermFreq(loadStopwords(STOPWORDS), readFile(new File(args[0])), MAX_NUM_OF_ENTRIES);
		tf.run(new PrintWriter(System.out));
		System.out.flush();
	}

	private static Set<String> loadStopwords(File file) throws IOException {
		List<String> stopWordsList = parseStopWords(readFile(file));
		return new HashSet<String>(stopWordsList);
	}

	private static List<String> parseStopWords(String src) throws IOException {
		return Arrays.asList(src.split(","));
	}

	private static String readFile(File f) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
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
