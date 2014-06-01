package johnathanrsnyder.json.parser;

import static org.junit.Assert.*;
import johnathanrsnyder.json.parser.Token.TokenType;

import org.junit.Test;

public class JsonTokenizerTest {

	@Test
	public void testNext() {
		String testString = "[{ \"hello\" : 45 }, true , false, null, { \"float\" : 3.24523} ] ";
		Tokenizer t = new Tokenizer(testString);
		Token token;
		Token lastNonNullToken = new Token("", TokenType.STRING);
		while ((token = t.next()) != null) {
			System.out.println(token.value);
			lastNonNullToken = token;
		}
		
		assertEquals(lastNonNullToken.value, "]");
	}
	

}
