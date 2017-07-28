package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.exception.RestApiException;
import com.avance.test.avancepaytest.exception.ServiceException;
import com.avance.test.avancepaytest.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DeviceDto> search(@RequestBody Map<String, Map<String, String>> fieldsCriteriaAndValues) throws RestApiException {
        try {
            return deviceService.search(fieldsCriteriaAndValues);
        } catch (ServiceException se) {
            throw new RestApiException(se.getMessage(), se.getErrorMessage(), se.getExceptionCause());
        }
    }

    //TODO: Delete this REST controller after a while -- useless, except for showing an example.
    //This is an example of how the body of the request should be sent from client.
    //The key of the first map is one of the fields that the search should be done.
    //The key of the inner Map is the criteria -> lt(less than), gt, start etc and the value of the inner map is the
    //value sent by the client.
    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Map<String, String>> ping() throws RestApiException {
        Map<String, Map<String, String>> res = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Map1_Value1", "Value1");
        map1.put("Map1_Value2", "Value2");
        map1.put("Map1_Value3", "Value3");
        res.put("Map1", map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("Map2_Value1", "Value1");
        map2.put("Map2_Value2", "Value2");
        map2.put("Map2_Value3", "Value3");
        res.put("Map3", map1);

        Map<String, String> map3 = new HashMap<>();
        map1.put("Map3_Value1", "Value1");
        map1.put("Map3_Value2", "Value2");
        map1.put("Map3_Value3", "Value3");
        res.put("Map3", map1);

        return res;
    }

    @RequestMapping(value = "/fields", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> getFieldsOfDeviceEntity() throws RestApiException {
        Map<String, String> result = new HashMap<>();
        try {
            result = deviceService.getFieldsOfDeviceEntity();
        } catch (ServiceException e) {
            throw new RestApiException(e.getMessage(), e.getErrorMessage(), e.getExceptionCause());
        }
        return result;
    }

}
