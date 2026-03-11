package com.example.HomeServerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDto {
	
	private long memoryUsage;
	private long freeMemory;
	private long totalMemory;
}
