package com.safetynet.alerts.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.Data;

@Component
public class DataHandler {
	
	private final ResourceLoader loader;
	private final Data data;
	
	public DataHandler(ResourceLoader loader) throws IOException {
		this.loader = loader;
		Resource resource = loader.getResource("classpath:data.json");
		File file = resource.getFile();
		String dataString = FileUtils.readFileToString(file, "UTF-8");
		data = JsonIterator.deserialize(dataString, Data.class);
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}
	
	public void save() throws IOException {
		String dataString = JsonStream.serialize(data);
		try (FileWriter writer = new FileWriter("classpath:data.json")){
			writer.write(dataString);
		}
	}

}
