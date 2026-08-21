package com.example.metric_api.formatter;

import com.example.metric_api.model.CpuMetric;

import java.util.Locale;

public final class MetricFormatter {

    private static final String[] UNITS = {"B","KB","MB","GB","TB"};

    private MetricFormatter(){}

    public static String formatTempeture(Integer value) {
        return String.format("%s", value + "°C");
    }

    public static String formatPercentange(Double value) {
        // %.2f virgülden sonra iki basamak gösterir
        // %% ise escape ile yüzde karakteri eklenir
        return String.format(Locale.US, "%.2f%%", value);
    }

    public static String formatBytes(Long bytes) {
        if (bytes == null) return null;
        double value = bytes;
        int unitIndex = 0;
        // kaç kez 1024 bölünebildiğini kontrol ederek hangi tipte (byte, kilobyte...) olduğu bulunuyor
        while (value >= 1024 && unitIndex < UNITS.length -1) {
            // 1024' böl ve unitIndex bir artır
            value /= 1024;
            unitIndex++;
        }
        // lokali us olarak formatlanmış değeri göster
        return String.format(Locale.US, "%.1f %s", value, UNITS[unitIndex]);
    }
}
