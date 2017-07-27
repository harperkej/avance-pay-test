package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.entity.DeviceEntity;
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
    public DeviceDto createOne(DeviceDto deviceDto) {
        DeviceEntity deviceToStore = this.convertDtoToEntity(deviceDto);
        DeviceEntity createdDevice = deviceRepository.createOne(deviceToStore);
        return this.convertEntityToDto(createdDevice);
    }

    @Override
    public List<DeviceDto> getAllDevicesWithLocationNoGreaterThan(int greaterThan) {
        List<DeviceDto> result = null;
        List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWithLocationNoGreaterThan(greaterThan);

        if (foundDevices != null) {
            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesWithLocationNoLessThanOrEqualTo(int lessThan) {
        List<DeviceDto> result = null;
        List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWithLocationNoLessThanOrEqualTo(lessThan);

        if (foundDevices != null) {
            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
        }
        return result;
    }

    @Override
    public List<DeviceDto> getAllDevicesWhereNameStartsWith(String name) {
        List<DeviceDto> result = null;
        List<DeviceEntity> foundDevices = this.deviceRepository.getAllDevicesWhereNameStartsWith(name);

        if (foundDevices != null) {
            result = new ArrayList<>();
            foundDevices.stream().map(DeviceServiceImpl::convertEntityToDto).forEach(result::add);
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
