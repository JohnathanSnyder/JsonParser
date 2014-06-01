package johnathanrsnyder.json;

public class JsonBool extends JsonValue {
	
	private boolean value;
	
	public JsonBool(boolean value) {
		this.value = value;
		this.type = JsonType.BOOL;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public String toString() {
		return (new Boolean(value)).toString();
	}

}
