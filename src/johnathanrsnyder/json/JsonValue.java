package johnathanrsnyder.json;

public class JsonValue {

	public enum JsonType {
		STRING,
		NUMBER,
		OBJECT,
		ARRAY,
		BOOL,
		NULL
	}
	
	protected JsonType type;
	
	public JsonValue() {
		this.type = JsonType.NULL;
	}
	
	public JsonType getType() {
		return type;
	}
	
	public String toString() {
		return "null";
	}
	
	
}
