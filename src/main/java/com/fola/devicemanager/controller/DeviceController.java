package com.fola.devicemanager.controller;

import com.fola.devicemanager.data.DeviceError;
import com.fola.devicemanager.data.DeviceResponse;
import com.fola.devicemanager.model.Device;
import com.fola.devicemanager.service.DeviceService;
import com.fola.devicemanager.service.DeviceUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/device")
@Validated
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> create(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.createDevice(device), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> update(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.updateDevice(device), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceResponse> find(@Valid @RequestBody Device device) {
        return new ResponseEntity<>(deviceService.findDevice(device), HttpStatus.OK);
    }

    @GetMapping(value = "/serial", params = {"serialNumber"})
    public ResponseEntity<DeviceResponse> findBySerialNumber(@RequestParam @Pattern(regexp = DeviceUtil.SERIAL_REGEX) @Valid String serialNumber) {
        return new ResponseEntity<>(deviceService.findDeviceBySerialNumber(serialNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/code", params = {"machineCode"})
    public ResponseEntity<DeviceResponse> findByMachineCode(@RequestParam @NotBlank @Valid String machineCode) {
        return new ResponseEntity<>(deviceService.findDeviceByMachineCode(machineCode), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DeviceResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        DeviceResponse deviceResponse = new DeviceResponse();
        ResponseEntity<DeviceResponse> responseEntity = new ResponseEntity<>(deviceResponse, HttpStatus.BAD_REQUEST);
        List<DeviceError> errors = deviceResponse.getErrors();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            errors.add(DeviceUtil.getDeviceError(fieldName));
        });
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DeviceResponse> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        DeviceResponse deviceResponse = new DeviceResponse();
        ResponseEntity<DeviceResponse> responseEntity = new ResponseEntity<>(deviceResponse, HttpStatus.BAD_REQUEST);
        List<DeviceError> errors = deviceResponse.getErrors();
        final Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        constraintViolations.forEach((v) -> {
            final String s = v.getPropertyPath().toString();
            if (s.endsWith("serialNumber")) {
                errors.add(DeviceUtil.SERIAL_NUMBER_INVALID_ERROR);
            } else if (s.endsWith("machineCode")) {
                errors.add(DeviceUtil.INVALID_MACHINE_CODE_ERROR);
            }
        });
        return responseEntity;
    }
}
