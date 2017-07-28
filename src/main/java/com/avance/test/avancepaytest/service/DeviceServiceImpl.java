package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.domain.Criteria;
import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.entity.DeviceEntity;
import com.avance.test.avancepaytest.exception.ExceptionCause;
import com.avance.test.avancepaytest.exception.RepositoryException;
import com.avance.test.avancepaytest.exception.ServiceException;
import com.avance.test.avancepaytest.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by a.kuci on 7/27/2017.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public DeviceDto createOne(DeviceDto deviceDto) throws ServiceException {
        try {
            DeviceEntity deviceToStore = this.convertDtoToEntity(deviceDto);
            DeviceEntity createdDevice = deviceRepository.createOne(deviceToStore);
            return this.convertEntityToDto(createdDevice);
        } catch (RepositoryException re) {
            throw new ServiceException(re.getMessage(), "Error storing the following device " + deviceDto, null);
        }
    }

    @Override
    public List<DeviceDto> search(Map<String, Map<String, String>> fieldsCriteriaAndValues) throws ServiceException {
        List<DeviceDto> result;
        boolean firstTimeInIteration = true;
        try {
            StringBuilder hibernateQuery = new StringBuilder();
            hibernateQuery.append("SELECT  d FROM DeviceEntity d WHERE");

            this.validateDeviceEntityFields(fieldsCriteriaAndValues.keySet());
            this.validateAllCriterias(fieldsCriteriaAndValues);
            //Build query
            for (String field : fieldsCriteriaAndValues.keySet()) {
                Map<String, String> crtierasAndValues = fieldsCriteriaAndValues.get(field);
                for (String criteriaString : crtierasAndValues.keySet()) {
                    Criteria criteria = Criteria.valueOf(criteriaString);
                    if (!firstTimeInIteration) {
                        hibernateQuery.append(" AND ");
                    }
                    firstTimeInIteration = false;
                    switch (criteria) {
                        case contain:
                            hibernateQuery.append(" d." + this.convertFirstLetterToLowerCase(field) + " LIKE '%" + crtierasAndValues.get(criteriaString) + "%' ");
                            break;
                        case end:
                            hibernateQuery.append(" d." + this.convertFirstLetterToLowerCase(field) + " LIKE '%" + crtierasAndValues.get(criteriaString) + "' ");
                            break;
                        case equals:
                            if (long.class.equals(getTypeOfTheFieldOfDeviceEntity(field))) {
                                hibernateQuery.append(" LENGTH(d." + this.convertFirstLetterToLowerCase(field) + ") = " + crtierasAndValues.get(criteriaString) + " ");
                            }
                            if (String.class.equals(getTypeOfTheFieldOfDeviceEntity(field))) {
                                hibernateQuery.append(" d." + this.convertFirstLetterToLowerCase(field) + " = '" + crtierasAndValues.get(criteriaString) + "' ");
                            }
                            break;
                        case gt:
                            hibernateQuery.append(" LENGTH(d." + this.convertFirstLetterToLowerCase(field) + ") >= " + crtierasAndValues.get(criteriaString) + " ");
                            break;
                        case lt:
                            hibernateQuery.append(" LENGTH(d." + this.convertFirstLetterToLowerCase(field) + ") <= " + crtierasAndValues.get(criteriaString) + " ");
                            break;
                        case start:
                            hibernateQuery.append(" d." + this.convertFirstLetterToLowerCase(field) + " LIKE '" + crtierasAndValues.get(criteriaString) + "%' ");
                            break;
                    }
                }
            }

            List<DeviceEntity> foundDevices = deviceRepository.search(hibernateQuery.toString());

            if (foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "Nothing found with given search criteria!", ExceptionCause.NO_RESOURCE_FOUND);
            } else {
                result = new ArrayList<>();
                foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
                return result;
            }
        } catch (RepositoryException re) {
            throw new ServiceException(re.getMessage(), re.getErrorMessage(), null);
        }
    }


    private void validateDeviceEntityFields(Set<String> fields) throws ServiceException {

        boolean areFieldsValid = false;
        for (String field : fields) {
            try {
                for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(DeviceEntity.class).getPropertyDescriptors()) {
                    //Get all getters of the Device entity.
                    Method method = propertyDescriptor.getReadMethod();
                    if (method.getName().replace("get", "").equals(field)) {
                        areFieldsValid = true;
                    }
                }
                if (!areFieldsValid) {
                    throw new ServiceException("One of the search fields is not valid.", "No such field " + field + " to search based on", ExceptionCause.INVALID_FIELD_NAME);
                }

            } catch (IntrospectionException e) {
                throw new ServiceException("Something bad happened while validating fields", e.getMessage(), ExceptionCause.INVALID_FIELD_NAME);
            }
        }
    }

    private void validateAllCriterias(Map<String, Map<String, String>> fieldsCriteriaAndValues) throws ServiceException {
        for (String field : fieldsCriteriaAndValues.keySet()) {
            Map<String, String> criteriaAndValues = fieldsCriteriaAndValues.get(field);
            for (String criteria : criteriaAndValues.keySet()) {
                try {
                    //If the provided criteria by client is not OK, stop searching - the below line will throw IllegalArgumentException.
                    Criteria criteriaToSearch = Criteria.valueOf(criteria);

                    Class typeOfField = getTypeOfTheFieldOfDeviceEntity(field);

                    if (typeOfField.equals(long.class)) {
                        if (criteriaToSearch.equals(Criteria.contain) || criteriaToSearch.equals(Criteria.start)
                                || criteriaToSearch.equals(Criteria.end)) {
                            throw new ServiceException("Invalid criteria!", "You cannot apply the criteria '" + criteriaToSearch + "' in numeric value", ExceptionCause.INVALID_CRITERIA);
                        }
                    }
                    if (typeOfField.equals(String.class)) {
                        if (criteriaToSearch.equals(Criteria.gt) || criteriaToSearch.equals(Criteria.lt)) {
                            throw new ServiceException("Invalid criteria!", "You cannot apply the criteria '" + criteriaToSearch + "' in string value", ExceptionCause.INVALID_CRITERIA);
                        }
                    }
                } catch (IllegalArgumentException exception) {
                    throw new ServiceException("No such criteria", "No such criteria exists to seach based on " + criteria, ExceptionCause.INVALID_CRITERIA);
                }
            }
        }
    }

    private Class getTypeOfTheFieldOfDeviceEntity(String field) throws ServiceException {

        Class typeOfField = null;
        try {
            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(DeviceEntity.class).getPropertyDescriptors()) {
                //Get all getters of the Device entity.
                Method method = propertyDescriptor.getReadMethod();
                if (method.getName().replace("get", "").equals(field)) {
                    typeOfField = method.getReturnType();
                }
            }
        } catch (IntrospectionException e) {
            throw new ServiceException("Something bad happened while validating fields", e.getMessage(), ExceptionCause.INVALID_FIELD_NAME);
        }
        return typeOfField;
    }

    private static DeviceDto convertEntityToDto(DeviceEntity deviceEntity) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setInsertedDateTime(deviceEntity.getInsertedDateTime());
        deviceDto.setName(deviceEntity.getName());
        deviceDto.setLocationNumber(deviceEntity.getLocationNumber());
        deviceDto.setId(deviceEntity.getId());
        return deviceDto;
    }

    private DeviceEntity convertDtoToEntity(DeviceDto deviceDto) {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(deviceDto.getId());
        deviceEntity.setInsertedDateTime(new Timestamp(System.currentTimeMillis()));
        deviceEntity.setLocationNumber(deviceDto.getLocationNumber());
        deviceEntity.setName(deviceDto.getName());
        return deviceEntity;
    }

    private String convertFirstLetterToLowerCase(String value) {
        String firstLetter = value.substring(0, 1);
        String valueWithoutFirstLetter = value.substring(1);
        String firstLetterToLowerCase = firstLetter.toLowerCase();
        return firstLetterToLowerCase + valueWithoutFirstLetter;
    }


    @Override
    public Map<String, String> getFieldsOfDeviceEntity() throws ServiceException {
        Map<String, String> result = new HashMap<>();
        try {
            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(DeviceEntity.class).getPropertyDescriptors()) {
                //Get all getters of the Device entity.
                Method method = propertyDescriptor.getReadMethod();
                result.put(method.getName().replace("get", ""), method.getReturnType().toString());
            }
        } catch (Exception e) {
            throw new ServiceException("An error occurred while reading the field and return types of each field from Device entit.", e.getMessage(), null);
        }
        return result;
    }

}
