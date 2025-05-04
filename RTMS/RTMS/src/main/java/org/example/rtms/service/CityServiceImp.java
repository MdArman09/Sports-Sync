package org.example.rtms.service;

import jakarta.transaction.Transactional;
import org.example.rtms.Mapper.CityMapper;
import org.example.rtms.dto.CityDTO;
import org.example.rtms.exceptions.AlreadyExistException.CityAlreadyExist;
import org.example.rtms.exceptions.ApiException;
import org.example.rtms.exceptions.NotFoundExceptions.CityNotFoundException;
import org.example.rtms.model.entity.City;
import org.example.rtms.repository.CityRepository;
import org.example.rtms.service.interfaces.ICityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImp implements ICityService {

    //Dependency injection
    CityRepository cityRepository;
    CityMapper cityMapper;
    public CityServiceImp(CityRepository cityRepository, CityMapper cityMapper){
        this.cityMapper = cityMapper;
        this.cityRepository = cityRepository;
    }

    //GET
    @Transactional
    @Override
    public List<CityDTO> getAllCity() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.cityListToCityDTOList(cities);
    }

    //GET
    @Transactional
    @Override
    public CityDTO getCityById(Long id) throws ApiException {
        City city = cityRepository.findById(id).orElseThrow(()-> new CityNotFoundException(id));
        return cityMapper.cityToCityDTO(city);
    }

    //PUT
    @Transactional
    @Override
    public CityDTO updateCityById(Long id, CityDTO cityDTO) throws ApiException {
        Optional<City> cityOptional = cityRepository.findById(id);

        return cityOptional.map(existingCity  -> {
            existingCity.setName(cityDTO.getName());

            cityRepository.save(existingCity);
            return cityMapper.cityToCityDTO(existingCity);
        }).orElseThrow(() -> new CityNotFoundException(id));
    }

    //POST
    @Transactional
    @Override
    public CityDTO createCity(CityDTO cityDTO) throws ApiException {
        // Converts TeamDTO a Team
        City city = cityMapper.cityDTOToCity(cityDTO);

        Optional<City> optionalCity = cityRepository.findByName(city.getName());

        if(optionalCity.isPresent()){
            throw new CityAlreadyExist(optionalCity.get().getName());
        }
        cityRepository.save(city);
        return cityMapper.cityToCityDTO(city);
    }

    //DELETE
    @Transactional
    @Override
    public CityDTO deleteCityById(Long id) throws ApiException {
        //Verifies if city exists, if not, throws CityNotFoundException
        Optional<City> optionalCity = cityRepository.findById(id);
        if(optionalCity.isPresent()){
            //Deletes city from DB
            cityRepository.delete(optionalCity.get());
            return cityMapper.cityToCityDTO(optionalCity.get());
        }
        throw new CityNotFoundException(id);
    }


}
