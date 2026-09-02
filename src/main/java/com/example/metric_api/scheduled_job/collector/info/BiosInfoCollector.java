package com.example.metric_api.scheduled_job.collector.info;

import com.example.metric_api.validator.MetricsValidator;
import org.springframework.stereotype.Component;

import com.example.metric_api.model.BiosInfo;

import oshi.SystemInfo;
import oshi.hardware.Firmware;

@Component
public class BiosInfoCollector {

    public BiosInfo collectBiosInfo(){

        BiosInfo biosInfo = new BiosInfo();
        SystemInfo si = new SystemInfo();
        Firmware firmware = si.getHardware().getComputerSystem().getFirmware();

        biosInfo.setManufacturer(MetricsValidator.validate(
                firmware.getManufacturer(),
                BiosInfoCollector.class,
                "manufacturer"
        ));
        biosInfo.setVersion(MetricsValidator.validate(
                firmware.getVersion(),
                BiosInfoCollector.class,
                "biosVersion"
        ));
        biosInfo.setReleaseDate(MetricsValidator.validate(
                firmware.getReleaseDate(),
                BiosInfoCollector.class,
                "biosReleaseDate"
        ));
        biosInfo.setBiosName(MetricsValidator.validate(
                firmware.getName(),
                BiosInfoCollector.class,
                "biosName"
        ));

        return biosInfo;
    }
}
