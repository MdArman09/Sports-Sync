//package org.example.rtms.service;
//
//import jakarta.transaction.Transactional;
//import org.example.rtms.Mapper.MatchRequestMapper;
//import org.example.rtms.Mapper.MatchResponseDTOMapper;
//import org.example.rtms.dto.request.MatchRequestDTO;
//import org.example.rtms.dto.response.MatchResponseDTO;
//import org.example.rtms.exceptions.ApiException;
//import org.example.rtms.exceptions.NotFoundExceptions.MatchNotFoundException;
//import org.example.rtms.exceptions.NotFoundExceptions.TeamNotFoundException;
//import org.example.rtms.exceptions.StadiumSizeException;
//import org.example.rtms.model.entity.Match;
//import org.example.rtms.model.entity.Team;
//import org.example.rtms.repository.MatchRepository;
//import org.example.rtms.repository.TeamRepository;
//import org.example.rtms.service.interfaces.IMatchService;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class MatchServiceImp implements IMatchService {
//
//    //Dependency injection
//    MatchRepository matchRepository;
//    TeamRepository teamRepository;
//    MatchResponseDTOMapper matchResponseDTOMapper;
//    MatchRequestMapper matchRequestMapper;
//    public MatchServiceImp(MatchRepository matchRepository, TeamRepository teamRepository, MatchRequestMapper matchRequestMapper, MatchResponseDTOMapper matchResponseDTOMapper){
//        this.matchRepository = matchRepository;
//        this.teamRepository = teamRepository;
//        this.matchRequestMapper = matchRequestMapper;
//        this.matchResponseDTOMapper = matchResponseDTOMapper;
//    }
//
//    //GET
//    @Transactional
//    @Override
//    public List<MatchResponseDTO> getAllMatches() {
//        List<Match> matches = matchRepository.findAll();
//        return matchResponseDTOMapper.matchListToMatchResponseDTOList(matches);
//    }
//
//    //GET
//    @Transactional
//    @Override
//    public MatchResponseDTO getMatchByUUID(UUID uuid) throws ApiException {
//        Match match = matchRepository.findById(uuid).orElseThrow(() -> new MatchNotFoundException(uuid));
//        return matchResponseDTOMapper.matchToMatchResponseDTO(match);
//    }
//
//    //DELETE
//    @Transactional
//    @Override
//    public MatchResponseDTO deleteMatch(UUID id) throws ApiException {
//        Optional<Match> match = matchRepository.findById(id);
//        if (match.isPresent()) {
//            matchRepository.delete(match.get());
//            return matchResponseDTOMapper.matchToMatchResponseDTO(match.get());
//        }
//        throw new MatchNotFoundException(id);
//    }
//
//    //POST
//    @Transactional
//    @Override
//    public MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) throws ApiException {
//        // Map MatchRequestDTO to Match
//        Match match = matchRequestMapper.matchRequestDTOtoMatch(matchRequestDTO);
//
//        //Search matches by their ID on th DB
//        Optional<Team> optionalHomeTeam = teamRepository.findById(match.getHomeTeam().getId());
//        Optional<Team> optionalAwayTeam = teamRepository.findById(match.getAwayTeam().getId());
//
//        Team homeTeam = optionalHomeTeam.orElseThrow(() -> new TeamNotFoundException(match.getHomeTeam().getId()));
//        Team awayTeam = optionalAwayTeam.orElseThrow(() -> new TeamNotFoundException(match.getAwayTeam().getId()));
//
//        //Set Random UUID
//        match.setUuid(UUID.randomUUID());
//        //Set HomeTeam stadium as match stadium
//        if(match.getSpectators() > homeTeam.getStadium().getCapacity()){
//            throw new StadiumSizeException(match.getSpectators(), homeTeam.getStadium());
//        }
//        match.setStadium(homeTeam.getStadium());
//        match.setHomeTeam(homeTeam);
//        match.setAwayTeam(awayTeam);
//        match.setTime(matchRequestDTO.getTime());
//        match.setDate(matchRequestDTO.getDate());
//        match.setHomeGoals(matchRequestDTO.getHomeGoals());
//        match.setAwayGoals(matchRequestDTO.getAwayGoals());
//        //Converts spectators number to BigDecimal
//        BigDecimal spectatorsBigDecimal = BigDecimal.valueOf(matchRequestDTO.getSpectators());
//        // Calculates match revenue multipliying spectators by TicketPrice
//        match.setRevenue(spectatorsBigDecimal.multiply(matchRequestDTO.getTicketPrice()));
//
//
//        Match matchResponse = matchRepository.save(match);
//
//        //Add match to HomeTeam and AwayTeam match lists.
//        addMatchToTeams(homeTeam, awayTeam, matchResponse);
//        return matchResponseDTOMapper.matchToMatchResponseDTO(matchResponse);
//
//    }
//
//    public void addMatchToTeams(Team homeTeam, Team awayTeam, Match match){
//        homeTeam.getHomeMatches().add(match);
//        awayTeam.getAwayMatches().add(match);
//    }
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public MatchServiceImp(MatchRepository matchRepository,
//                           TeamRepository teamRepository,
//                           MatchRequestMapper matchRequestMapper,
//                           MatchResponseDTOMapper matchResponseDTOMapper,
//                           SimpMessagingTemplate messagingTemplate) {
//        this.matchRepository = matchRepository;
//        this.teamRepository = teamRepository;
//        this.matchRequestMapper = matchRequestMapper;
//        this.matchResponseDTOMapper = matchResponseDTOMapper;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//}


package org.example.rtms.service;

import jakarta.transaction.Transactional;
import org.example.rtms.Mapper.MatchRequestMapper;
import org.example.rtms.Mapper.MatchResponseDTOMapper;
import org.example.rtms.dto.request.MatchRequestDTO;
import org.example.rtms.dto.response.MatchResponseDTO;
import org.example.rtms.exceptions.ApiException;
import org.example.rtms.exceptions.NotFoundExceptions.MatchNotFoundException;
import org.example.rtms.exceptions.NotFoundExceptions.TeamNotFoundException;
import org.example.rtms.exceptions.StadiumSizeException;
import org.example.rtms.model.entity.Match;
import org.example.rtms.model.entity.Team;
import org.example.rtms.repository.MatchRepository;
import org.example.rtms.repository.TeamRepository;
import org.example.rtms.service.interfaces.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchServiceImp implements IMatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchResponseDTOMapper matchResponseDTOMapper;
    private final MatchRequestMapper matchRequestMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MatchServiceImp(MatchRepository matchRepository,
                           TeamRepository teamRepository,
                           MatchRequestMapper matchRequestMapper,
                           MatchResponseDTOMapper matchResponseDTOMapper,
                           SimpMessagingTemplate messagingTemplate) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.matchRequestMapper = matchRequestMapper;
        this.matchResponseDTOMapper = matchResponseDTOMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    @Override
    public List<MatchResponseDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matchResponseDTOMapper.matchListToMatchResponseDTOList(matches);
    }

    @Transactional
    @Override
    public MatchResponseDTO getMatchByUUID(UUID uuid) throws ApiException {
        Match match = matchRepository.findById(uuid)
                .orElseThrow(() -> new MatchNotFoundException(uuid));
        return matchResponseDTOMapper.matchToMatchResponseDTO(match);
    }

    @Transactional
    @Override
    public MatchResponseDTO deleteMatch(UUID id) throws ApiException {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isPresent()) {
            matchRepository.delete(match.get());
            return matchResponseDTOMapper.matchToMatchResponseDTO(match.get());
        }
        throw new MatchNotFoundException(id);
    }

    @Transactional
    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) throws ApiException {
        Match match = matchRequestMapper.matchRequestDTOtoMatch(matchRequestDTO);

        Optional<Team> optionalHomeTeam = teamRepository.findById(match.getHomeTeam().getId());
        Optional<Team> optionalAwayTeam = teamRepository.findById(match.getAwayTeam().getId());

        Team homeTeam = optionalHomeTeam.orElseThrow(() -> new TeamNotFoundException(match.getHomeTeam().getId()));
        Team awayTeam = optionalAwayTeam.orElseThrow(() -> new TeamNotFoundException(match.getAwayTeam().getId()));

        match.setUuid(UUID.randomUUID());

        if (match.getSpectators() > homeTeam.getStadium().getCapacity()) {
            throw new StadiumSizeException(match.getSpectators(), homeTeam.getStadium());
        }

        match.setStadium(homeTeam.getStadium());
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setTime(matchRequestDTO.getTime());
        match.setDate(matchRequestDTO.getDate());
        match.setHomeGoals(matchRequestDTO.getHomeGoals());
        match.setAwayGoals(matchRequestDTO.getAwayGoals());

        BigDecimal spectatorsBigDecimal = BigDecimal.valueOf(matchRequestDTO.getSpectators());
        match.setRevenue(spectatorsBigDecimal.multiply(matchRequestDTO.getTicketPrice()));

        Match savedMatch = matchRepository.save(match);

        addMatchToTeams(homeTeam, awayTeam, savedMatch);

        // 🔴 Send real-time update to clients
        messagingTemplate.convertAndSend("/topic/events", matchResponseDTOMapper.matchToMatchResponseDTO(savedMatch));

        return matchResponseDTOMapper.matchToMatchResponseDTO(savedMatch);
    }

    public void addMatchToTeams(Team homeTeam, Team awayTeam, Match match) {
        homeTeam.getHomeMatches().add(match);
        awayTeam.getAwayMatches().add(match);
    }
}
