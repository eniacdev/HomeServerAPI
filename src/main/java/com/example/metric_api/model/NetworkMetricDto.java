package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NetworkMetricDto {
    
    private String interfaceName;
    private Long bytesRecv;
    private Long bytesSent;
    private Long inErrors;
    private Long outErrors;

}
