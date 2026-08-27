package com.example.metric_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetworkMetricDto {
    
    private String interfaceName;
    private Long bytesRecv;
    private String bytesRecvFormatted;
    private Long bytesSent;
    private String bytesSentFormatted;
    private Long inErrors;
    private Long outErrors;

}
