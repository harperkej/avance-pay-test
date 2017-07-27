package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;
import org.hibernate.Session;
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

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public DeviceEntity createOne(DeviceEntity deviceEntity) {
        Session session = getSession();
        return (DeviceEntity) session.merge(deviceEntity);
    }

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoGreaterThan(int greaterThan) {
        Session session = getSession();
        Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "LENGTH(d.locationNumber) > :greaterThan");
        query.setParameter("greaterThan", greaterThan);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) {
        Session session = getSession();
        Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "LENGTH(d.locationNumber) <= :lessThan");
        query.setParameter("lessThan", lessThan);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesWhereNameStartsWith(String name) {
        Session session = getSession();
        Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "d.name LIKE :name");
        name = name + "%";
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesWhereNameEndsWith(String name) {
        Session session = getSession();
        Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "d.name LIKE :name");
        name = "%" + name;
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<DeviceEntity> getAllDevicesThatContain(String name) {
        Session session = getSession();
        Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                "d.name LIKE :name");
        name = "%" + name + "%";
        query.setParameter("name", name);
        return query.getResultList();
    }

}
