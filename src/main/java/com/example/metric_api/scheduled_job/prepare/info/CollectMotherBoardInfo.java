package com.example.metric_api.scheduled_job.prepare.info;

import org.springframework.stereotype.Component;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.MotherBoardInfoDto;
import com.example.metric_api.response.ResponseType;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;

@Component
public class CollectMotherBoardInfo {

    public MotherBoardInfoDto collectMotherBoardInfo(){

        SystemInfo si = new SystemInfo();
        MotherBoardInfoDto motherBoard = new MotherBoardInfoDto();

        ComputerSystem cs = si.getHardware().getComputerSystem();

        Baseboard baseboard = cs.getBaseboard();
        motherBoard.setModel(baseboard.getModel());
        motherBoard.setManufacturer(baseboard.getManufacturer());
        motherBoard.setSerial(baseboard.getSerialNumber());

        checkInfo(motherBoard); // kontroll

        return motherBoard;
    }

    private void checkInfo(MotherBoardInfoDto motherBoard){

        if(motherBoard.getManufacturer() == null &&
          motherBoard.getModel() == null &&
          motherBoard.getSerial() == null){
            throw new BaseException(ResponseType.SYSTEM_INFO_NOT_COLLECTED);
        }
    }
}
