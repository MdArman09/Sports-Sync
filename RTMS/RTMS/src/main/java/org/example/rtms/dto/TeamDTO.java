package org.example.rtms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.rtms.dto.response.MatchResponseDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TeamDTO {
    private Long id;
    private String name;
    private DivisionDTO division;
    private CityDTO city;
    private StadiumDTO stadium;
    private List<MatchResponseDTO> homeMatches;
    private List<MatchResponseDTO> awayMatches;
}
