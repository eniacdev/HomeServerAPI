package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.check.MetricsValidator;
import com.example.metric_api.model.NetworkInfo;
import com.example.metric_api.scheduled_job.prepare.metrics.NetworkMetricCollector;
import oshi.SystemInfo;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

@Component
public class NetworkInfoCollector {
    public NetworkInfo collectNetworkInfo() throws Exception{
        
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> netwoIFs = hal.getNetworkIFs();
        NetworkInfo networkInfo = new NetworkInfo();

        for (NetworkIF net : netwoIFs) {

            net.updateAttributes();

            networkInfo.setInterfaceName(MetricsValidator.validate(
                    net.getName(),
                    NetworkMetricCollector.class,
                    "interfaceName"
            ));
            networkInfo.setMacAddr(MetricsValidator.validate(
                    net.getMacaddr(),
                    NetworkMetricCollector.class,
                    "macAddr"
            ));
            networkInfo.setIpv4Addr(Arrays.asList(net.getIPv4addr()));
            networkInfo.setSpeed(MetricsValidator.validate(
                    net.getSpeed(),
                    NetworkMetricCollector.class,
                    "speed"
            ));
            networkInfo.setMtu(MetricsValidator.validate(
                    net.getMTU(),
                    NetworkMetricCollector.class,
                    "mtu"
            ));
        }
        networkInfo.setHostname(MetricsValidator.validate(
                InetAddress.getLocalHost().getHostName(),
                NetworkMetricCollector.class,
                "hostname"
        ));

        return networkInfo;
    }
}
