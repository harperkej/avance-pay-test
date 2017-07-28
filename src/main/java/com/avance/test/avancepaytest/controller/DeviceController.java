package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.exception.RestApiException;
import com.avance.test.avancepaytest.exception.ServiceException;
import com.avance.test.avancepaytest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by a.kuci on 7/27/2017.
 */
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public DeviceDto createOne(@RequestBody DeviceDto deviceDto) throws RestApiException {
        try {
            return deviceService.createOne(deviceDto);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(value = "/locationNumber/lessThan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(@RequestParam("lessThan") int lessThan) throws RestApiException {
        try {
            return this.deviceService.getAllDevicesWithLocationNoLessThanOrEqualTo(lessThan);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(value = "/locationNumber/greaterThan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(@RequestParam("greaterThan") int greaterThan) throws RestApiException {
        try {
            return this.deviceService.getAllDevicesWithLocationNoGreaterThan(greaterThan);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(value = "/deviceName/startWith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWhereNameStartsWith(@RequestParam("name") String name) throws RestApiException {
        try {
            return this.deviceService.getAllDevicesWhereNameStartsWith(name);
        } catch (ServiceException re) {
            throw new RestApiException(re.getMessage(), re.getErrorMessage(), re.getExceptionCause());
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(value = "/deviceName/endsWith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWhereNameEndsWith(@RequestParam("name") String name) throws RestApiException {
        try {
            return this.deviceService.getAllDevicesWhereNameEndsWith(name);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(value = "/deviceName/contain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesThatContain(@RequestParam("name") String name) throws RestApiException {
        try {
            return this.deviceService.getAllDevicesThatContain(name);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

}
