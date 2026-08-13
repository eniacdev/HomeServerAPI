package com.example.metric_api.scheduled_job.export;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import com.example.metric_api.dto.JsonFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.dto.SystemMetricsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrepareJsonFile {

	private static final Logger log = LoggerFactory.getLogger(PrepareJsonFile.class);
	private final ObjectMapper objectMapper;

	public PrepareJsonFile(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}
	
	public boolean writeJsonFile(SystemMetricsResponse metric) {
		
		try {
		
		log.warn("The json file is being preparing.");

		JsonFileResponse jsonFile = new JsonFileResponse();
		
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
		//metric.setJsonFile(jsonFile);

		Files.createDirectories(directoryPath);
		
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), metric);

		log.info("file is ready.");
		
		return true;
		
		}catch (Exception e) {
			log.error("Json dosyası yazılamadı: {}", e.getMessage());
		    return false;
		}
	}
}
