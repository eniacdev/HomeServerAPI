package com.example.metric_api.scheduled_job.export;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.example.metric_api.model.JsonFile;
import com.example.metric_api.model.MetricsSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileBuilder {

	private static final Logger log = LoggerFactory.getLogger(JsonFileBuilder.class);
	private final ObjectMapper objectMapper;

	public JsonFileBuilder(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}
	
	public boolean exportToJsonFile(MetricsSnapshot metric) {
		
		try {
		
		log.info("The json file is being preparing.");

		JsonFile jsonFile = new JsonFile();
		
		LocalDate date = LocalDate.now();
		String year = String.valueOf(date.getYear());
		String month = String.format("%02d", date.getMonthValue());
		
		String fileName = date.toString() + ".json";
		
		Path directoryPath = Paths.get("MetricsLog", year, month);
		Path filePath = directoryPath.resolve(fileName);

		//hem client için hemde kaydedilecek JSON dosyası için dosyanın ismi ve oluşturulduğu tarihi ekler.
		//client için faydalı olabilir.
		jsonFile.setFile(filePath.toString());
		jsonFile.setCreatedAt(date);

		Files.createDirectories(directoryPath);
		
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), metric);

		log.info("file is ready.");
		
		return true;
		
		}catch (Exception e) {
			log.error("something went wrong, json file is not created: {}", e.getMessage());
		    return false;
		}
	}
}
