package org.example.rtms.Mapper;

import org.example.rtms.dto.StadiumDTO;
import org.example.rtms.model.entity.Stadium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StadiumMapper {
    StadiumDTO stadiumToStadiumDTO(Stadium stadium);
    Stadium stadiumDTOToStadium(StadiumDTO stadiumDTO);
    List<StadiumDTO> stadiumListToStadiumDTOList(List<Stadium> stadiums);


}
