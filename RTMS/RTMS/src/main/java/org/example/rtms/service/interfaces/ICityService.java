package org.example.rtms.service.interfaces;

import org.example.rtms.dto.CityDTO;
import org.example.rtms.exceptions.ApiException;

import java.util.List;

public interface ICityService {
    //Get
    List<CityDTO> getAllCity();
    CityDTO getCityById(Long id) throws ApiException;

    //Put
    CityDTO updateCityById(Long id, CityDTO cityDTO) throws ApiException;

    //Post
    CityDTO createCity(CityDTO cityDTO) throws ApiException;

    //Delete
    CityDTO deleteCityById(Long id) throws ApiException;
}
