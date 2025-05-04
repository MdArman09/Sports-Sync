package org.example.rtms.service.interfaces;


import org.example.rtms.dto.StadiumDTO;
import org.example.rtms.exceptions.ApiException;

import java.util.List;

public interface IStadiumService {

    //Get
    List<StadiumDTO> getAllStadiums();
    StadiumDTO getStadiumById(Long id) throws ApiException;

    //Put
    StadiumDTO updateStadiumById(Long id, StadiumDTO stadiumDTO) throws ApiException;

    //Post
    StadiumDTO createStadium(StadiumDTO stadiumDTO) throws ApiException;

    //Delete
    StadiumDTO deleteStadiumById(Long id) throws ApiException;
}
