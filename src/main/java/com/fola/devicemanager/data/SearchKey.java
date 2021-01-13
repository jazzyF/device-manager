package com.fola.devicemanager.data;

import com.fola.devicemanager.service.DeviceUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SearchKey {

    @Pattern(regexp = DeviceUtil.SERIAL_REGEX)
    private String serialNumber;
    @NotBlank
    private String machineCode;

    public SearchKey(@Pattern(regexp = DeviceUtil.SERIAL_REGEX) String serialNumber, @NotBlank String machineCode) {
        this.serialNumber = serialNumber;
        this.machineCode = machineCode;
    }

    public SearchKey(){
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
