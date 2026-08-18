package com.example.metric_api.check;

import com.example.metric_api.model.CpuMetric;
import com.example.metric_api.model.MemoryMetric;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.prepare.metrics.SystemMetricsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MetricsValidator {

    private MetricsValidator(){}

    private static final Logger log = LoggerFactory.getLogger(MetricsValidator.class);

    public static Double validate (Double value, Class<?> clazz , String metricName) {
        if (value == null || value.isNaN() || value.isInfinite() || value < 0) {
            log.error("{} in {} is null, NaN or negative.", clazz.getSimpleName(), metricName);
            throw new BaseException(ResponseType.METRICS_IS_INVALID);
        }
        return value;
    }

    public static Long validate(Long value, Class<?> clazz,  String metricName) {
        if (value == null || value < 0) {
            log.error("{} in {} is null or negative.", clazz.getSimpleName(), metricName);
            throw new BaseException(ResponseType.METRICS_IS_INVALID);
        }
        return value;
    }

    public static String validate(String value, Class<?> clazz, String metricName) {
        if (value == null || value.isBlank()) {
            log.error("{} in {} is null or blank", clazz.getSimpleName(), metricName);
            throw new BaseException(ResponseType.METRICS_IS_INVALID);
        }
        return value;
    }
}
