package com.example.metric_api.check;

import com.example.metric_api.model.CpuMetric;
import com.example.metric_api.model.MemoryMetric;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;

public final class CheckInfoAndMetrics {

    private CheckInfoAndMetrics(){}

    public static void checkCpuMetrics(CpuMetric cpuMetric) {
        if (cpuMetric.getProcessCpuLoad() == null &&
            cpuMetric.getSystemCpuLoad() == null &&
            cpuMetric.getSystemAverageLoad() == null &&
            cpuMetric.getCpuTemp() == null) {
            throw new BaseException(ResponseType.CPU_METRICS_NOT_COLLECTED);
        }
    }

    public static void checkMemoryMetrics(MemoryMetric memoryMetric) {

    }
}
