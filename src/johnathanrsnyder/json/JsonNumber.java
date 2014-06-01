package johnathanrsnyder.json;


public class JsonNumber extends JsonValue {
	
	public enum JsonNumberType {
		INT,
		FLOAT
	}
	
	protected JsonNumberType numberType;
	
	public JsonNumber() {
		this.type = JsonType.NUMBER;
	}
	
	public JsonNumberType getNumberType() {
		return numberType;
	}
	
	

}
