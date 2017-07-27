package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoGreaterThan(int greaterThan) {
        Query query = entityManager.createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "LENGTH(d.locationNumber) > :greaterThan");
        query.setParameter("greaterThan", greaterThan);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) {
        Query query = entityManager.createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "LENGTH(d.locationNumber) <= :lessThan");
        query.setParameter("lessThan", lessThan);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesWhereNameStartsWith(String name) {
        Query query = entityManager.createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "d.name LIKE :name");
        name = name + "%";
        query.setParameter("name", name);
        return query.getResultList();
    }

}
