package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;

import java.util.List;

/**
 * Created by a.kuci on 7/27/2017.
 */
public interface DeviceService {

    public DeviceDto createOne(DeviceDto deviceDto);

    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(int greaterThan);

    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan);

    public List<DeviceDto> getAllDevicesWhereNameStartsWith(String name);

    public List<DeviceDto> getAllDevicesWhereNameEndsWith(String name);

    public List<DeviceDto> getAllDevicesThatContain(String name);


}
