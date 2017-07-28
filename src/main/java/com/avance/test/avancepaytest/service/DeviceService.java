package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by a.kuci on 7/27/2017.
 */
public interface DeviceService {

    public DeviceDto createOne(DeviceDto deviceDto) throws ServiceException;

    public List<DeviceDto> search(Map<String, Map<String, String>> fieldsCriteriaAndValues) throws ServiceException;

    public Map<String, String> getFieldsOfDeviceEntity() throws ServiceException;

}
