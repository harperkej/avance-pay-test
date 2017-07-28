package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;
import com.avance.test.avancepaytest.exception.RepositoryException;

import java.util.List;

/**
 * Created by a.kuci on 7/27/2017.
 */
public interface DeviceRepository {

    public DeviceEntity createOne(DeviceEntity deviceEntity) throws RepositoryException;

    public List<DeviceEntity> getAllDevicesWithLocationNoGreaterThan(int greaterThan) throws RepositoryException;

    public List<DeviceEntity> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) throws RepositoryException;

    public List<DeviceEntity> getAllDevicesWhereNameStartsWith(String name) throws RepositoryException;

    public List<DeviceEntity> getAllDevicesWhereNameEndsWith(String name) throws RepositoryException;

    public List<DeviceEntity> getAllDevicesThatContain(String name) throws RepositoryException;


}
