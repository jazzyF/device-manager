package com.fola.devicemanager.service;

import com.fola.devicemanager.data.DeviceResponse;
import com.fola.devicemanager.model.Device;
import org.springframework.stereotype.Service;

@Service
public interface DeviceService {

    DeviceResponse createDevice(Device device);

    DeviceResponse updateDevice(Device device);

    DeviceResponse findDevice(Device device);

    DeviceResponse findDeviceBySerialNumber(String serialNumber);

    DeviceResponse findDeviceByMachineCode(String machineCode);

}
