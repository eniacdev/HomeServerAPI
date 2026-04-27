package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.model.MotherBoardInfoDto;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;

public class CollectMotherBoardInfo {

    public MotherBoardInfoDto collectMotherBoardInfo(){

        SystemInfo si = new SystemInfo();
        MotherBoardInfoDto motherBoard = new MotherBoardInfoDto();

        ComputerSystem cs = si.getHardware().getComputerSystem();
        motherBoard.setManufacturer(cs.getManufacturer());
        motherBoard.setModel(cs.getModel());

        Baseboard baseboard = cs.getBaseboard();
        motherBoard.setMotherboard(baseboard.getModel());
        motherBoard.setMotherboard(baseboard.getManufacturer());
        motherBoard.setSerial(baseboard.getSerialNumber());

        //kontrol blokları çok uzar mı? ...

        return motherBoard; //şimdilik bu şekilde, method hazır değil.
    }
}
