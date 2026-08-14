package com.example.metric_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JsonFile {

    private String file;
    private LocalDate createdAt;
}
