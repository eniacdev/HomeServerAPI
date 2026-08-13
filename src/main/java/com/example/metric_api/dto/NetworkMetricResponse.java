package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NetworkMetricResponse {
    
    private String interfaceName;
    private Long bytesRecv;
    private Long bytesSent;
    private Long inErrors;
    private Long outErrors;

}
