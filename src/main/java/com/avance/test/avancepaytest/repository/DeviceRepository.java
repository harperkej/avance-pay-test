package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;

import java.util.List;

/**
 * Created by a.kuci on 7/27/2017.
 */
public interface DeviceRepository {

    public DeviceEntity createOne(DeviceEntity deviceEntity);

    public List<DeviceEntity> getAllDevicesWithLocationNoGreaterThan(int greaterThan);

    public List<DeviceEntity> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan);


}
