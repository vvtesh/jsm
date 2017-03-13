package in.ac.iiitd.pag.util;

import java.util.HashSet;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.EnglishStemmer;

public class Stemmer {
	public static String stem(String word) {
		String stemmedEntity = word;
		EnglishStemmer s = new EnglishStemmer();
		String stemmedTitle="";
		
		s.setCurrent(word);
		s.stem();
			
		return s.getCurrent();
	}
	
	public static String stemWithLucene(String word) {
		String stemmedEntity = word;
		
		
		EnglishAnalyzer en_an = new EnglishAnalyzer(Version.LUCENE_34);
		QueryParser parser = new QueryParser(Version.LUCENE_34, "", en_an);
		String processedQuery = null;
		try {			
			processedQuery = parser.parse(word).toString();
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		stemmedEntity = processedQuery;
					
		return stemmedEntity;
	}
}
