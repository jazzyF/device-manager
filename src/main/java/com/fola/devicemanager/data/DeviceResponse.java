package com.fola.devicemanager.data;

import com.fola.devicemanager.model.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeviceResponse {
    private Set<Device> devices;
    private final List<DeviceError> errors = new ArrayList<>();

    public DeviceResponse(Set<Device> devices) {
        this.devices = devices;
    }

    public DeviceResponse() {
    }

    public DeviceResponse(DeviceError deviceError) {
        errors.add(deviceError);
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public List<DeviceError> getErrors() {
        return errors;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
