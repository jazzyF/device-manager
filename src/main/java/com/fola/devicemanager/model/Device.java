package com.fola.devicemanager.model;

import com.fola.devicemanager.service.DeviceUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Device {

    @Pattern(regexp = DeviceUtil.SERIAL_REGEX)
    private final String serialNumber;
    @NotBlank
    private final String machineCode;
    private String deviceName;

    public Device(String serialNumber, String machineCode, String deviceName) {
        this.serialNumber = serialNumber;
        this.machineCode = machineCode;
        this.deviceName = deviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device device = (Device) o;
        return getSerialNumber().equals(device.getSerialNumber()) && getMachineCode().equals(device.getMachineCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getMachineCode());
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
