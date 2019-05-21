
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanFilter;
import org.apache.lucene.analysis.ko.KoreanTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class Main {

	public static void main(String[] args) throws Exception {

		String res = morphAnalyzer("��ٶ��� �δ� �ٴ�");
		
		System.out.println(res);
	}

	public static String morphAnalyzer(String message) throws Exception {
		KoreanTokenizer koreanTokenizer = new KoreanTokenizer(); // tokenizer ����.
		koreanTokenizer.setReader(new StringReader(message));
		TokenStream tokenStream = new KoreanFilter(koreanTokenizer); // filter ����.

		CharTermAttribute termAtt = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAttr = tokenStream.addAttribute(TypeAttribute.class);
		String sumString = "";
		try {
			tokenStream.reset();

			while (tokenStream.incrementToken()) {
				 //System.out.println(termAtt.toString() + " [" + typeAttr.type() + "]");
				sumString += " " + termAtt.toString();
			}
			tokenStream.end();
		} finally {
			tokenStream.close();
			System.out.println(sumString);
		}
		return sumString;
	}
}
