package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.exception.ServiceException;

import java.util.List;

/**
 * Created by a.kuci on 7/27/2017.
 */
public interface DeviceService {

    public DeviceDto createOne(DeviceDto deviceDto) throws ServiceException;

    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(int greaterThan) throws ServiceException;

    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) throws ServiceException;

    public List<DeviceDto> getAllDevicesWhereNameStartsWith(String name) throws ServiceException;

    public List<DeviceDto> getAllDevicesWhereNameEndsWith(String name) throws ServiceException;

    public List<DeviceDto> getAllDevicesThatContain(String name) throws ServiceException;


}
