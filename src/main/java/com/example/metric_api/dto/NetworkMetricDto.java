package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NetworkMetricDto {
    
    private String interfaceName;
    private Long bytesRecv;
    private String bytesRecvFormatted;
    private Long bytesSent;
    private String bytesSentFormatted;
    private Long inErrors;
    private Long outErrors;

}
