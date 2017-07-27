package com.avance.test.avancepaytest.service;

import com.avance.test.avancepaytest.dto.DeviceDto;
import com.avance.test.avancepaytest.entity.DeviceEntity;
import com.avance.test.avancepaytest.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

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

    private DeviceDto convertEntityToDto(DeviceEntity deviceEntity) {
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
