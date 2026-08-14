package com.example.metric_api.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JsonFile {

    private String file;
    private LocalDate createdAt;
}
