package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.entity.DeviceEntity;
import com.avance.test.avancepaytest.exception.ExceptionCause;
import com.avance.test.avancepaytest.exception.RepositoryException;
import com.avance.test.avancepaytest.exception.ServiceException;
import com.avance.test.avancepaytest.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
            throw new ServiceException(re.getMessage(), "Error storing the followind device " + deviceDto, ExceptionCause.ERROR_STORING_RESOURCE);
        }
    }

    @Override
    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(int greaterThan) throws ServiceException {
        List<DeviceDto> result = null;
        try {
            List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWithLocationNoGreaterThan(greaterThan);

            //If nothing is found, throw an exception.
            if (foundDevices == null || foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "No device exists with the location number greater than " + greaterThan, ExceptionCause.NO_RESOURCE_FOUND);
            }
            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        } catch (RepositoryException e) {
            throw new ServiceException("An error occurred when trying to find devices with location number greater than " + greaterThan, e.getMessage(), ExceptionCause.NO_RESOURCE_FOUND);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) throws ServiceException {
        List<DeviceDto> result = null;
        try {
            List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWithLocationNoLessThanOrEqualTo(lessThan);

            if (foundDevices == null || foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "No data found when searching for devices with location number less than " + lessThan, ExceptionCause.NO_RESOURCE_FOUND);
            }
            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        } catch (RepositoryException re) {
            throw new ServiceException("An error occurred while trying to search for devices with location number less than " + lessThan, re.getErrorMessage(), ExceptionCause.NO_RESOURCE_FOUND);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesWhereNameStartsWith(String name) throws ServiceException {
        List<DeviceDto> result = null;
        try {
            List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWhereNameStartsWith(name);

            if (foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "No device found with name that starts with " + name, ExceptionCause.NO_RESOURCE_FOUND);
            }

            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        } catch (RepositoryException re) {
            throw new ServiceException(re.getMessage(), "Something went wrong when searching for devices with name that starts with " + name, ExceptionCause.NO_RESOURCE_FOUND);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesWhereNameEndsWith(String name) throws ServiceException {
        List<DeviceDto> result = null;
        try {

            List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWhereNameEndsWith(name);

            if (foundDevices == null || foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "No device found that it's name ends with " + name, ExceptionCause.NO_RESOURCE_FOUND);
            }

            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        } catch (RepositoryException re) {
            throw new ServiceException(re.getMessage(), re.getErrorMessage(), null);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesThatContain(String name) throws ServiceException {
        List<DeviceDto> result = null;
        try {


            List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesThatContain(name);

            if (foundDevices.isEmpty()) {
                throw new ServiceException("No data found!", "No device found that it's name contains " + name, ExceptionCause.NO_RESOURCE_FOUND);
            }

            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        } catch (RepositoryException re) {
            throw new ServiceException(re.getMessage(), re.getErrorMessage(), null);
        }
        return result;
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

}
