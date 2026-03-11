package com.example.HomeServerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OsDto {

	private String osName;
	private String osVersion;
}
