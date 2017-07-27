package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by a.kuci on 7/27/2017.
 */
@Transactional
@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @Autowired
    EntityManager entityManager;


    @Override
    public DeviceEntity createOne(DeviceEntity deviceEntity) {
        return entityManager.merge(deviceEntity);
    }


}
