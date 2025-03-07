package TestFramework.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// Read data json to string
		String JsonData = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//main//java//TestFramework//Data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// String to HashMap - need to use jackson data bind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

}
