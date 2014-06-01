package johnathanrsnyder.json.parser;

public class Token {
	
	public enum TokenType {
		NUMBER,
		STRING,
		BOOL,
		NULL,
		OBRACE,
		CBRACE,
		OBRACKET,
		CBRACKET,
		COLON,
		COMMA
	}
	
	public String value;
	public int ival;
	public float fval;
	public boolean bval;
	public TokenType type;
	private boolean floatFlag;
	
	public Token(String value, TokenType type) {
		this.value = value;
		this.type = type;
		
		if (type == TokenType.NUMBER) {
			setIntOrFloat();
		} else if (type == TokenType.BOOL) {
			setTrueOrFalse();
		}
	}
	
	public boolean isFloat() {
		return floatFlag;
	}
	
	private void setIntOrFloat() {
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == '.') {
				floatFlag = true;
				fval = Float.parseFloat(value);
				return;
			}
		}
		
		floatFlag = false;
		ival = Integer.parseInt(value);
	}
	
	private void setTrueOrFalse() {
		if (value.charAt(0) == 't') {
			bval = true;
		} else {
			bval = false;
		}
	}

}
