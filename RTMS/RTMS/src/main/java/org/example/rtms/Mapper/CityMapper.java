package org.example.rtms.Mapper;

import org.example.rtms.dto.CityDTO;
import org.example.rtms.model.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {
    CityDTO cityToCityDTO(City city);
    City cityDTOToCity(CityDTO cityDTO);
    List<CityDTO> cityListToCityDTOList(List<City> cities);
}
