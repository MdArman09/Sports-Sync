package org.example.rtms.Mapper;

import org.example.rtms.dto.request.MatchRequestDTO;
import org.example.rtms.model.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatchRequestMapper {

    Match matchRequestDTOtoMatch(MatchRequestDTO matchRequestDTO);
    MatchRequestDTO matchToMatchRequestDTO(Match match);
    List<MatchRequestDTO> matchListToMatchRequestDTOList(List<Match> matches);
}
