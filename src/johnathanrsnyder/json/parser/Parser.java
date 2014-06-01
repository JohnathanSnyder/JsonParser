package johnathanrsnyder.json.parser;

import johnathanrsnyder.json.JsonArray;
import johnathanrsnyder.json.JsonBool;
import johnathanrsnyder.json.JsonFloat;
import johnathanrsnyder.json.JsonInt;
import johnathanrsnyder.json.JsonObject;
import johnathanrsnyder.json.JsonString;
import johnathanrsnyder.json.JsonValue;
import johnathanrsnyder.json.parser.Token.TokenType;

public class Parser {
	
	private Tokenizer tokenizer;
	private Token currToken;
	
	public Parser(String input) {
		tokenizer = new Tokenizer(input);
		currToken = tokenizer.next();
	}
	
	public JsonValue parse() {
		return jsonValue();
	}
	
	public JsonValue jsonValue() {
		Token t;
		if (check(TokenType.STRING)) {
			t = match(TokenType.STRING);
			return new JsonString(t.value);
		} else if (check(TokenType.NUMBER)) {
			t = match(TokenType.NUMBER);
			if (t.isFloat()) {
				return new JsonFloat(t.fval);
			} else {
				return new JsonInt(t.ival);
			}
		} else if (objectPending()) {
			return jsonObject();
		} else if (arrayPending()) {
			return jsonArray();
		} else if (check(TokenType.BOOL)) {
			t = match(TokenType.BOOL);
			return new JsonBool(t.bval);
		} else {
			match(TokenType.NULL);
			return new JsonValue();
		}
	}
	
	public JsonObject jsonObject() {
		match(TokenType.OBRACE);
		JsonObject obj = new JsonObject();
		JsonString str;
		JsonValue val;
		if (check(TokenType.STRING)) {
			str = new JsonString(match(TokenType.STRING).value);
			match(TokenType.COLON);
			val = jsonValue();
			obj.addField(str, val);
			while (check(TokenType.COMMA)) {
				match(TokenType.COMMA);
				str = new JsonString(match(TokenType.STRING).value);
				match(TokenType.COLON);
				val = jsonValue();
				obj.addField(str, val);
			}
		}
		match(TokenType.CBRACE);
		return obj;
	}
	
	public JsonArray jsonArray() {
		match(TokenType.OBRACKET);
		JsonArray array = new JsonArray();
		JsonValue val;
		if (valuePending()) {
			val = jsonValue();
			array.append(val);
			while (check(TokenType.COMMA)) {
				match(TokenType.COMMA);
				val = jsonValue();
				array.append(val);
			}
		}
		match(TokenType.CBRACKET);
		
		return array;
	}
	
	private boolean valuePending() {
		return objectPending() || arrayPending() || check(TokenType.STRING) ||
				check(TokenType.NUMBER) || check(TokenType.BOOL) || check(TokenType.NULL);
	}
	
	private boolean objectPending() {
		return check(TokenType.OBRACE);
	}
	
	private boolean arrayPending() {
		return check(TokenType.OBRACKET);
	}
	
	private boolean check(Token.TokenType type) {
		return currToken.type == type;
	}
	
	private Token match(Token.TokenType type) {
		if (type == currToken.type) {
			Token returnToken = currToken;
			currToken = tokenizer.next();
			return returnToken;
		} else {
			return null;
		}
	}

}
