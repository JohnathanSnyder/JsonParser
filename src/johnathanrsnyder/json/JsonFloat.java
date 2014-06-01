package johnathanrsnyder.json;

public class JsonFloat extends JsonNumber {
	
	private float value;
	
	public JsonFloat(float value) {
		super();
		this.numberType = JsonNumberType.FLOAT;
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public String toString() {
		return (new Float(value)).toString();
	}

}
