package com.example.metric_api.scheduled_job.prepare.metrics;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.metric_api.dto.NetworkMetricResponse;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

@Component
public class CollectNetworkMetric {
    public NetworkMetricResponse collectNetworkMetric(){

        SystemInfo si = new SystemInfo();
        NetworkMetricResponse networkMetric = new NetworkMetricResponse();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> netwoIFs = hal.getNetworkIFs();
        
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
