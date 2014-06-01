package johnathanrsnyder.json;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonObject extends JsonValue {
	
	private Map<JsonString, JsonValue> objectField;
	
	public JsonObject() {
		type = JsonType.OBJECT;
		objectField = new HashMap<JsonString, JsonValue>();
	}
	
	public void addField(JsonString id, JsonValue value) {
		objectField.put(id,value);
	}
	
	public Collection<JsonString> getFieldNames() {
		return objectField.keySet();
	}
	
	public Iterator<JsonString> fieldNameIterator() {
		return objectField.keySet().iterator();
	}
	
	public JsonValue getValue(JsonString id) {
		return objectField.get(id);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<JsonString> iter = fieldNameIterator();
		sb.append("{");
		while (iter.hasNext()) {
			JsonString fieldName = iter.next();
			JsonValue fieldValue = objectField.get(fieldName);
			sb.append(fieldName.toString());
			sb.append(" : ");
			sb.append(fieldValue.toString());
			if (iter.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	

}
