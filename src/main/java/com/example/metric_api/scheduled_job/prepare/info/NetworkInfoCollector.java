package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.model.NetworkInfo;
import oshi.SystemInfo;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

@Component
public class NetworkInfoCollector {
    public NetworkInfo collectNetworkInfo(){
        
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> netwoIFs = hal.getNetworkIFs();
        NetworkInfo networkInfo = new NetworkInfo();

        for (NetworkIF net : netwoIFs) {

            net.updateAttributes();

            networkInfo.setInterfaceName(net.getName());
            networkInfo.setMacAddr(net.getMacaddr());
            networkInfo.setIpv4Addr(Arrays.asList(net.getIPv4addr()));
            networkInfo.setSpeed(net.getSpeed());
            networkInfo.setMtu(net.getMTU());

        }
        return networkInfo;
    }
}
