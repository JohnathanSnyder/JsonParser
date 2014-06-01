package johnathanrsnyder.json;

public class JsonInt extends JsonNumber {
	
	private int value;
	
	public JsonInt(int value) {
		super();
		this.numberType = JsonNumberType.INT;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return (new Integer(value)).toString();
	}

}
