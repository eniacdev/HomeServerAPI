package com.example.metric_api.formatter;

import com.example.metric_api.model.CpuMetric;

import java.util.Locale;

public final class MetricFormatter {

    private static final String[] UNITS = {"B","KB","MB","GB","TB"};

    private MetricFormatter(){}

    public static void format(CpuMetric cpu){
        if (cpu == null) return;
        //cpu.getProcessCpuLoadFormatted(formatBytes(cpu.getProcessCpuLoad()));
    }

    private static String formatBytes(Long bytes) {
        if (bytes == null) return null;
        double value = bytes;
        int unitIndex = 0;
        while(value >= 1024 && unitIndex < UNITS.length - 1) {
            value /= 1024;
            unitIndex++;
        }
        return String.format(Locale.US, "%.1f %s", value, UNITS[unitIndex]);
    }

    private static String formatPercent(Double percentValue) {
        if (percentValue == null) return null;
        return String.format(Locale.US, "%%%.1f",percentValue);
    }
}
