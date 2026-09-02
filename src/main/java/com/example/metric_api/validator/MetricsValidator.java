package com.example.metric_api.validator;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;

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

    public static void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new BaseException(ResponseType.DATE_TYPE_ERROR);
        }
        if (startDate.isAfter(endDate) || endDate.isAfter(LocalDate.now())) {
            throw new BaseException(ResponseType.DATE_TYPE_ERROR);
        }
    }
}
