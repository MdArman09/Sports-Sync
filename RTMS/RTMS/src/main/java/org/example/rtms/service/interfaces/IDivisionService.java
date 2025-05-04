package org.example.rtms.service.interfaces;


import org.example.rtms.dto.DivisionDTO;
import org.example.rtms.exceptions.ApiException;

import java.util.List;

public interface IDivisionService {
    //Get
    List<DivisionDTO> getAllDivision();
    DivisionDTO getDivisionById(Long id) throws ApiException;

    //Put
    DivisionDTO updateDivisionById(Long id, DivisionDTO divisionDTO) throws ApiException;

    //Post
    DivisionDTO createDivision(DivisionDTO divisionDTO) throws ApiException;

    //Delete
    DivisionDTO deleteDivisionById(Long id) throws ApiException;
}
