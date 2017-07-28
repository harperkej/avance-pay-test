package com.avance.test.avancepaytest.repository;

import com.avance.test.avancepaytest.entity.DeviceEntity;
import com.avance.test.avancepaytest.exception.RepositoryException;
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
    public DeviceEntity createOne(DeviceEntity deviceEntity) throws RepositoryException {
        try {
            Session session = getSession();
            //set the id and store the resource
            deviceEntity.setId((Long) session.save(deviceEntity));
            return deviceEntity;
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoGreaterThan(int greaterThan) throws RepositoryException {
        try {
            Session session = getSession();
            Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                    "LENGTH(d.locationNumber) > :greaterThan");
            query.setParameter("greaterThan", greaterThan);
            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException("An error occurred when trying to get the devices with location number greater than " + greaterThan);
        }
    }

    @Override
    public List<DeviceEntity> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) throws RepositoryException {
        try {
            Session session = getSession();
            Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                    "LENGTH(d.locationNumber) <= :lessThan");
            query.setParameter("lessThan", lessThan);
            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException("An error occurred when searching for devices with location number less than or equal to " + lessThan);
        }
    }

    @Override
    public List<DeviceEntity> getAllDevicesWhereNameStartsWith(String name) throws RepositoryException {
        try {
            Session session = getSession();
            Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                    "d.name LIKE :name");
            name = name + "%";
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException("An error occurred when searching for devices whose name starts with " + name);
        }
    }

    @Override
    public List<DeviceEntity> getAllDevicesWhereNameEndsWith(String name) throws RepositoryException {
        try {
            Session session = getSession();
            Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                    "d.name LIKE :name");
            name = "%" + name;
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception re) {
            throw new RepositoryException("An error occurred when searching for devices whose name ends with " + name);
        }
    }

    @Override
    public List<DeviceEntity> getAllDevicesThatContain(String name) throws RepositoryException {
        try {
            Session session = getSession();
            Query query = getSession().createQuery("SELECT d FROM DeviceEntity d WHERE " +
                    "d.name LIKE :name");
            name = "%" + name + "%";
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException("An error occurred when searching for devices that contain value " + name + " in their name");
        }
    }

}
