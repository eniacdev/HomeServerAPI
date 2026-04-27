package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherBoardDto {

    private String manufacturer;
    private String model;
    private String motherboard;
    private String vendor;
    private String serial;

}
