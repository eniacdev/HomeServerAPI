package com.example.metric_api.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class NetworkMetric {
    
    private String interfaceName;
    private Long bytesRecv;
    private Long bytesSent;
    private Long inErrors;
    private Long outErrors;

}
