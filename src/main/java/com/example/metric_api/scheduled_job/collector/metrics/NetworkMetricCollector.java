package com.example.metric_api.scheduled_job.collector.metrics;
import java.util.List;

import com.example.metric_api.validator.MetricsValidator;
import org.springframework.stereotype.Component;

import com.example.metric_api.model.NetworkMetric;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

@Component
public class NetworkMetricCollector {
    public NetworkMetric collectNetworkMetric(){

        SystemInfo si = new SystemInfo();
        NetworkMetric networkMetric = new NetworkMetric();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> netwoIFs = hal.getNetworkIFs();
        
        for (NetworkIF net : netwoIFs) {
            net.updateAttributes();

            networkMetric.setInterfaceName(MetricsValidator.validate(
                    net.getName(),
                    NetworkMetricCollector.class,
                    "interfaceName"
            ));
            networkMetric.setBytesRecv(MetricsValidator.validate(
                    net.getBytesRecv(),
                    NetworkMetricCollector.class,
                    "bytesRecv"
            ));
            networkMetric.setBytesSent(MetricsValidator.validate(
                    net.getBytesSent(),
                    NetworkMetricCollector.class,
                    "bytesSent"
            ));
            networkMetric.setInErrors(MetricsValidator.validate(
                    net.getInErrors(),
                    NetworkMetricCollector.class,
                    "inErrors"
            ));
            networkMetric.setOutErrors(MetricsValidator.validate(
                    net.getOutErrors(),
                    NetworkMetricCollector.class,
                    "outErrors"
            ));

        }
        return networkMetric;
    }
}
