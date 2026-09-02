package com.example.metric_api.scheduled_job.collector.info;

import com.example.metric_api.validator.MetricsValidator;
import org.springframework.stereotype.Component;
import com.example.metric_api.model.MotherBoardInfo;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;

@Component
public class MotherBoardInfoCollector {

    public MotherBoardInfo collectMotherBoardInfo(){

        SystemInfo si = new SystemInfo();
        MotherBoardInfo motherBoard = new MotherBoardInfo();

        ComputerSystem cs = si.getHardware().getComputerSystem();

        Baseboard baseboard = cs.getBaseboard();
        motherBoard.setModel(MetricsValidator.validate(
                baseboard.getModel(),
                MotherBoardInfoCollector.class,
                "model"));
        motherBoard.setManufacturer(MetricsValidator.validate(
                baseboard.getManufacturer(),
                MotherBoardInfoCollector.class,
                "manufacturer"
        ));
        motherBoard.setSerial(MetricsValidator.validate(
                baseboard.getSerialNumber(),
                MotherBoardInfoCollector.class,
                "serial"
        ));

        return motherBoard;
    }
}
