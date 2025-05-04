package org.example.rtms.service.interfaces;


import org.example.rtms.dto.request.MatchRequestDTO;
import org.example.rtms.dto.response.MatchResponseDTO;
import org.example.rtms.exceptions.ApiException;

import java.util.List;
import java.util.UUID;

public interface IMatchService {

    //Get
    List<MatchResponseDTO> getAllMatches();
    MatchResponseDTO getMatchByUUID(UUID uuid) throws ApiException;

    //Post
    MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) throws ApiException;

    //Delete Match
    MatchResponseDTO deleteMatch(UUID id) throws ApiException;
}
