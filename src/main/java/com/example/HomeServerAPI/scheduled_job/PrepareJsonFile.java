package com.example.HomeServerAPI.scheduled_job;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrepareJsonFile {
	
	public boolean writeJsonFile(SystemLogDto metric) {
		
		try {
		
		System.out.println("dosya yazılmaya başlandı");
			
		ObjectMapper mapper = new ObjectMapper();
		
		LocalDate date = LocalDate.now();
		String year = String.valueOf(date.getYear());
		
		String fileName = date.toString() + ".json";
		
		Path directoryPath = Paths.get("/MetricsLog", year);
		Path filePath = directoryPath.resolve(fileName);
		
		Files.createDirectories(directoryPath);
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), metric);	
		
		System.out.println("dosya yazma bitti");
		
		return true;
		
		}catch (Exception e) {
			System.out.println("dosya yazılırken bir hata oluştu: " + e);
			return false;
		}
		
	}
}
