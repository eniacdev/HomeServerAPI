package com.example.metric_api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NetworkInfoDto {

    //static
    private String interfaceName;
    private String macAddr;
    private List<String> ipv4Addr;
    private Long speed;
    private Long mtu;
    
}
