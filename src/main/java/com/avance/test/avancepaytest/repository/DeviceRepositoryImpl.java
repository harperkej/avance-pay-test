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
    public List<DeviceEntity> search(String stringQuery) throws RepositoryException {
        try {
            Session session = getSession();

            Query query = session.createQuery(stringQuery);

            return query.getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }


}
