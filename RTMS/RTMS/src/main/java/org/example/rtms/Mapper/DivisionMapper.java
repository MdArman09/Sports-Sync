package org.example.rtms.Mapper;

import org.example.rtms.dto.DivisionDTO;
import org.example.rtms.model.entity.Division;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DivisionMapper {
    DivisionDTO divisionToDivisionDTO(Division division);
    Division divisionDTOToDivision(DivisionDTO divisionDTO);
    List<DivisionDTO> divisionListToDivisionDTOList(List<Division> divisionList);
}
