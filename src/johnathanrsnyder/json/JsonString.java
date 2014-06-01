package johnathanrsnyder.json;


public class JsonString extends JsonValue {
	
	private String value;
	
	public JsonString(String value) {
		this.value = value;
		this.type = JsonType.STRING;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}

}
