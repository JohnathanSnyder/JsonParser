package johnathanrsnyder.json.parser;

import johnathanrsnyder.json.parser.Token.TokenType;

public class Tokenizer {
	
	private char[] input;
	private int index;

	public Tokenizer(String input) {
		this.input = input.toCharArray();
		index = 0;
	}
	
	public Token next() {
		if (index >= input.length) {
			return null;
		}
		skipWhitespace();
		
		if (index >= input.length) {
			return null;
		}
		
		char c = input[index];
		
		if (c == '"') {
			return readString();
		} else if (c == '-' || Character.isDigit(c)) {
			return readNumber();
		} else if (c == '{') {
			index++;
			return new Token("{", TokenType.OBRACE);
		} else if (c == '}') {
			index++;
			return new Token("}", TokenType.CBRACE);
		} else if (c == '[') {
			index++;
			return new Token("[", TokenType.OBRACKET);
		} else if (c == ']') {
			index++;
			return new Token("]", TokenType.CBRACKET);
		} else if (c == ',') {
			index++;
			return new Token(",", TokenType.COMMA);
		} else if (c == ':') {
			index++;
			return new Token(":", TokenType.COLON);
		} else {
			return readId();
		}
		
	}
	
	private boolean isAfterBackslash(char c) {
		return c == '"' || c == '\\' || c == '/' || c == 'b' || c == 'f' ||
		 		c == 'n' || c == 'r' || c == 't';
	}
	
	private Token readId() {
		StringBuilder value = new StringBuilder();
		Token.TokenType type = TokenType.STRING;
		
		while (index < input.length && !Character.isWhitespace(input[index])
				&& input[index] != ',' && input[index] != ':') {
			value.append(input[index]);
			index++;
		}
		
		if (value.toString().equals("true") || value.toString().equals("false")) {
			type = TokenType.BOOL;
		} else if (value.toString().equals("null")) {
			type = TokenType.NULL;
		} else if (value.toString().equals("")) {
			return null;
		}
		
		return new Token(value.toString(), type);
	}
	
	
	private Token readNumber() {
		StringBuilder value = new StringBuilder();
		char c = input[index];
		if (c == '-') {
			index++;
			value.append(c);
		}
		
		c = input[index];
		while (Character.isDigit(c)) {
			value.append(c);
			index++;
			c = input[index];
			if (c == '.') {
				value.append(c);
				index++;
				c = input[index];
				break;
			}
		}
		
		while (Character.isDigit(c)) {
			value.append(c);
			index++;
			c = input[index];
			
			if (c == 'e' || c == 'E') {
				value.append(c);
				index++;
				c = input[index];
				if (c == '+' || c == '-') {
					value.append(c);
					index++;
					c = input[index];
				}
				
				while (Character.isDigit(c)) {
					value.append(c);
					index++;
					c = input[index];
				}
			}
		}
		
		return new Token(value.toString(), TokenType.NUMBER);
	}
	
	private Token readString() {
		StringBuilder value = new StringBuilder();
		char c = input[index];
		index++;
		if (c == '"') {
			c = input[index];
			while (c != '"') {
				value.append(c);
				if (c == '\\') {
					index++;
					c = input[index];
					if (isAfterBackslash(c)) {
						value.append(c);
					}
				}

				index++;
				c = input[index];
			}
			index++;
		}
		return new Token(value.toString(), TokenType.STRING);
	}
	
	private void skipWhitespace() {
		while (index < input.length && Character.isWhitespace(input[index])) {
			index++;
		}
	}
	

}
