package com.fola.devicemanager.service;

import com.fola.devicemanager.data.DeviceError;

import java.util.HashMap;
import java.util.Map;

public final class DeviceUtil {
    public static final String SERIAL_REGEX = "([0-9]|[a-z]|[A-Z]){2}\\-([0-9]|[a-z]|[A-Z]){4}|([0-9]|[a-z]|[A-Z]){7}\\-([0-9]|[a-z]|[A-Z]){5}|([0-9]|[a-z]|[A-Z])\\-([0-9]|[a-z]|[A-Z]){8}";

    public static final DeviceError INVALID_MACHINE_CODE_ERROR = new DeviceError("machine.code.invalid", "ER001", "The machine code is incorrect. Check the Machine code you provided and try again.");

    public static final DeviceError MACHINE_CODE_NOT_FOUND_ERROR = new DeviceError("machine.code.not.found", "ER002", "The machine code does not match our records.");

    public static final DeviceError SERIAL_NUMBER_INVALID_ERROR = new DeviceError("serial.number.invalid", "ER0023", "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");

    public static final DeviceError SERIAL_NUMBER_NOT_FOUND_ERROR = new DeviceError("serial.number.not.found", "ER004", "The serial number does not match our records.");

    private static final Map<String, DeviceError> ERROR_MAP = new HashMap<>();

    static {
        ERROR_MAP.put("serialNumber", SERIAL_NUMBER_INVALID_ERROR);
        ERROR_MAP.put("machineCode", INVALID_MACHINE_CODE_ERROR);
    }

    public static String buildKey(String serialNumber, String machineCode) {
        return ("{sn:" + serialNumber + ",mc:" + machineCode + "}").toLowerCase();
    }

    public static DeviceError getDeviceError(String fieldName) {
        if ("serialNumber".equals(fieldName)) {
            return SERIAL_NUMBER_INVALID_ERROR;
        } else {
            return INVALID_MACHINE_CODE_ERROR;
        }
    }
}
