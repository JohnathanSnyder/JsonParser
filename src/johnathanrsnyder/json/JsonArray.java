package johnathanrsnyder.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArray extends JsonValue {
	
	private List<JsonValue> array;
	
	public JsonArray() {
		this.type = JsonType.ARRAY;
		array = new ArrayList<JsonValue>();
	}
	
	public void append(JsonValue value) {
		array.add(value);
	}
	
	public Iterator<JsonValue> iterator() {
		return array.iterator();
	}
	
	public int size() {
		return array.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<JsonValue> iter = iterator();
		sb.append("[ ");
		while (iter.hasNext()) {
			JsonValue value = iter.next();
			sb.append(value.toString());
			if (iter.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}


}
