package com.example.metric_api.scheduled_job.prepare.metrics;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.metric_api.model.NetworkMetricDto;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

@Component
public class CollectNetworkMetric {
    public NetworkMetricDto collectNetworkMetric(){

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> netwoIFs = hal.getNetworkIFs();
        NetworkMetricDto networkMetric = new NetworkMetricDto();
        
        for (NetworkIF net : netwoIFs) {
            net.updateAttributes();

            networkMetric.setInterfaceName(net.getName());
            networkMetric.setBytesRecv(net.getBytesRecv());
            networkMetric.setBytesSent(net.getBytesSent());
            networkMetric.setInErrors(net.getInErrors());
            networkMetric.setOutErrors(net.getOutErrors());

        }



        return networkMetric;
    }
    
}
