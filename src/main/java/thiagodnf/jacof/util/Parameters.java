package thiagodnf.jacof.util;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

	protected Map<String, String> parameters;

	public Parameters() {
		this.parameters = new HashMap<>();
	}
	
	public void put(String key, String value){
		this.parameters.put(key, value);
	}
	
	public int getInt(String key, int defaultValue) {
		
		if (this.parameters.containsKey(key) && this.parameters.get(key) != null) {
			return Integer.valueOf(this.parameters.get(key));
		}

		return defaultValue;
	}
	
	public double getDouble(String key, double defaultValue) {

		if (this.parameters.containsKey(key) && this.parameters.get(key) != null) {
			return Double.valueOf(this.parameters.get(key));
		}

		return defaultValue;
	}
	
	public String getString(String key, String defaultValue) {

		if (this.parameters.containsKey(key)) {
			return this.parameters.get(key);
		}

		return defaultValue;
	}
	
	public String getString(String key) {
		return this.getString(key, null);
	}

	@Override
	public String toString() {
		return this.parameters.toString();
	}
	
	
}
