package com.fola.devicemanager.service;

import com.fola.devicemanager.data.DeviceError;
import com.fola.devicemanager.model.Device;
import com.fola.devicemanager.dao.DeviceRepository;
import com.fola.devicemanager.data.DeviceResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.fola.devicemanager.service.DeviceUtil.SERIAL_NUMBER_NOT_FOUND_ERROR;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceResponse createDevice(Device device) {
        deviceRepository.save(device);
        Set<Device> devices = new HashSet<>(1);
        devices.add(device);
        return new DeviceResponse(devices);
    }

    @Override
    public DeviceResponse updateDevice(Device device) {
        if (deviceRepository.update(device) == null) {
            return new DeviceResponse(SERIAL_NUMBER_NOT_FOUND_ERROR);
        } else {
            Set<Device> devices = new HashSet<>(1);
            devices.add(device);
            return new DeviceResponse(devices);
        }
    }

    @Override
    public DeviceResponse findDevice(Device device) {
        final Set<Device> devices = deviceRepository.findBySerialNumberAndMachineCode(device.getSerialNumber(), device.getMachineCode());
        final DeviceResponse deviceResponse = new DeviceResponse();
        deviceResponse.setDevices(devices);
        if (devices.isEmpty()) {
            return new DeviceResponse(DeviceUtil.MACHINE_CODE_NOT_FOUND_ERROR);
        } else {
            return deviceResponse;
        }
    }

    @Override
    public DeviceResponse findDeviceBySerialNumber(String serialNumber) {
        final Set<Device> devices = deviceRepository.findBySerialNumber(serialNumber);
        if (devices.isEmpty()) {
            return new DeviceResponse(SERIAL_NUMBER_NOT_FOUND_ERROR);
        } else {
            return new DeviceResponse(devices);
        }
    }

    @Override
    public DeviceResponse findDeviceByMachineCode(String machineCode) {
        final Set<Device> devices = deviceRepository.findByMachineCode(machineCode);
        if (devices.isEmpty()) {
            return new DeviceResponse(DeviceUtil.MACHINE_CODE_NOT_FOUND_ERROR);
        } else {
            return new DeviceResponse(devices);
        }
    }
}
