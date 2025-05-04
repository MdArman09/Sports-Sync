package org.example.rtms.Mapper;

import org.example.rtms.dto.response.MatchResponseDTO;
import org.example.rtms.model.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatchResponseDTOMapper {
    MatchResponseDTO matchToMatchResponseDTO(Match match);
    Match matchResponseDTOToMatch(MatchResponseDTO matchResponseDTO);
    List<MatchResponseDTO> matchListToMatchResponseDTOList(List<Match> match);
}
