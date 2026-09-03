package com.example.metric_api.scheduled_job.collector.info;

import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.GpuInfo;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.validator.MetricsValidator;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class GpuInfoCollector {

    private final HardwareAbstractionLayer hardware = new SystemInfo().getHardware();

    public List<GpuInfo> collectGpuInfo(){
        List<GraphicsCard> graphicsCards = hardware.getGraphicsCards();

        if (graphicsCards.isEmpty()) {
            throw new BaseException(ResponseType.GPU_INFO_NOT_COLLECTED);
        }

        List<GpuInfo> gpuList = new ArrayList<>();

        for (GraphicsCard card : graphicsCards) {
            GpuInfo gpuInfo = new GpuInfo();

            gpuInfo.setDeviceId(MetricsValidator.validate(
                    card.getDeviceId(),
                    GpuInfoCollector.class,
                    "deviceId"
            ));
            gpuInfo.setGpuName(MetricsValidator.validate(
                    card.getName(),
                    GpuInfoCollector.class,
                    "gpuName"
            ));
            gpuInfo.setVendor(MetricsValidator.validate(
                    card.getVendor(),
                    GpuInfoCollector.class,
                    "vendor"
            ));
            gpuInfo.setVram(MetricsValidator.validate(
                    card.getVRam(),
                    GpuInfoCollector.class,
                    "vram"
            ));
            gpuList.add(gpuInfo);
        }

        return gpuList;
    }
}
