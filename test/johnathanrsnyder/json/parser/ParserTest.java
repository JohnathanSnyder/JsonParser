package johnathanrsnyder.json.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import johnathanrsnyder.json.*;
import johnathanrsnyder.json.JsonValue.JsonType;

public class ParserTest {

	@Test
	public void test() {
		String testString = "[{ \"hello\" : 45 }, true , false, null, { \"float\" : 3.24523 } ] ";
		Parser parser = new Parser(testString);
		JsonValue value = parser.parse();
		System.out.println(value);
		assertEquals(value.getType(), JsonType.ARRAY);
		
	}

}
