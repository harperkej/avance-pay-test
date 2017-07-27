package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public DeviceDto createOne(@RequestBody DeviceDto deviceDto) {
        return deviceService.createOne(deviceDto);
    }

    @RequestMapping(value = "/locationNumber/lessThan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(@RequestParam("lessThan") int lessThan) {
        return this.deviceService.getAllDevicesWithLocationNoLessThanOrEqualTo(lessThan);
    }

    @RequestMapping(value = "/locationNumber/greaterThan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(@RequestParam("greaterThan") int greaterThan) {
        return this.deviceService.getAllDevicesWithLocationNoGreaterThan(greaterThan);
    }

    @RequestMapping(value = "/locationNumber/startWith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWhereNameStartsWith(@RequestParam("name") String name) {
        return this.deviceService.getAllDevicesWhereNameStartsWith(name);
    }

    @RequestMapping(value = "/locationNumber/endsWith", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesWhereNameEndsWith(@RequestParam("name") String name) {
        return this.deviceService.getAllDevicesWhereNameEndsWith(name);
    }


    @RequestMapping(value = "/locationNumber/contain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevicesThatContain(@RequestParam("name") String name) {
        return this.deviceService.getAllDevicesThatContain(name);
    }


}
