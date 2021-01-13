package com.fola.devicemanager.dao;

import com.fola.devicemanager.model.Device;

import java.util.Set;

public interface DeviceRepository {

    Set<Device> findBySerialNumberAndMachineCode(String serialNumber, String machineCode);

    Set<Device> findBySerialNumber(String serialNumber);

    Set<Device> findByMachineCode(String machineCode);

    Device save(Device device);

    Device update(Device device);

}
