package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}