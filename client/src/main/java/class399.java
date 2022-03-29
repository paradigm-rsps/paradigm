import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class class399 implements class398 {
	JSONObject field4385;

	public class399(byte[] var1) throws UnsupportedEncodingException {
		this.method7231(var1);
	}

	public byte[] vmethod7229() throws UnsupportedEncodingException {
		return this.field4385 == null ? new byte[0] : this.field4385.toString().getBytes(StandardCharsets.UTF_8);
	}

	void method7231(byte[] var1) throws UnsupportedEncodingException {
		String var2 = new String(var1, StandardCharsets.UTF_8);
		this.method7224(var2);
	}

	void method7224(String var1) throws UnsupportedEncodingException {
		try {
			if (var1.charAt(0) == '{') {
				this.field4385 = new JSONObject(var1);
			} else {
				if (var1.charAt(0) != '[') {
					throw new UnsupportedEncodingException("Invalid JSON passed to the JSON content builder.");
				}

				JSONArray var2 = new JSONArray(var1);
				this.field4385 = new JSONObject();
				this.field4385.put("arrayValues", var2);
			}

		} catch (JSONException var3) {
			throw new UnsupportedEncodingException(var3.getMessage());
		}
	}

	public JSONObject method7225() {
		return this.field4385;
	}
}
