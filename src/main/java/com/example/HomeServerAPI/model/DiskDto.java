package com.example.HomeServerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskDto {
	
	private long diskUsage;
	private long freeDisk;
	private long totalDisk;
}
