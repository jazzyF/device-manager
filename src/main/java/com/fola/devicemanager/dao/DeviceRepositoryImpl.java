package com.fola.devicemanager.dao;

import com.fola.devicemanager.model.Device;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    private static final Set<Device> DEVICE_SET = new HashSet<>();

    @Override
    public Set<Device> findBySerialNumberAndMachineCode(String serialNumber, String machineCode) {
        return DEVICE_SET.stream().
                filter(x -> x.getSerialNumber().equals(serialNumber) && x.getMachineCode().equals(machineCode)).
                collect(Collectors.toSet());
    }

    @Override
    public Set<Device> findBySerialNumber(String serialNumber) {
        return DEVICE_SET.stream().
                filter(x -> x.getSerialNumber().equals(serialNumber)).
                collect(Collectors.toSet());
    }

    @Override
    public Set<Device> findByMachineCode(String machineCode) {
        return DEVICE_SET.stream().
                filter(x -> x.getMachineCode().equals(machineCode)).
                collect(Collectors.toSet());
    }

    @Override
    public Device save(Device device) {
        DEVICE_SET.add(device);
        return device;
    }

    @Override
    public Device update(Device device) {
        if (DEVICE_SET.contains(device)) {
            DEVICE_SET.remove(device);
            DEVICE_SET.add(device);
            return device;
        } else {
            return null;
        }
    }

    @PostConstruct
    public void seedData() {
        Device I_PHONE_X = new Device("12-1222", "IPHONE-WBNAF", "iPhone X");
        Device PLAY_STATION_5 = new Device("3455670-22222", "PLAYSTATION422", "Playstation 5");
        Device IPAD_AIR = new Device("1-00022221", "IPAD-AIR-4253", "iPad Air");

        DEVICE_SET.add(I_PHONE_X);
        DEVICE_SET.add(PLAY_STATION_5);
        DEVICE_SET.add(IPAD_AIR);
    }
}
