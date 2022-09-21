package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.TravelCreateDto;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Repository.TravelRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class TravelService {
	private final TravelRepository travelRepository;

    @Transactional
    public TravelCreateDto.Response createTravel(TravelCreateDto.Request request) {
        Travel travel = Travel.builder()
                .name(request.getName())
                .build();
        travelRepository.save(travel);
        return TravelCreateDto.Response.fromEntity(travel);
    }

    public TravelCreateDto.Response getTravelInfo(int no){
        Optional<Travel> info = travelRepository.findById(Long.valueOf(no));
        return TravelCreateDto.Response.fromEntity(info.get());
    }

    public TravelCreateDto.HomeView getTravelToMainView(int travelId){
        Optional<Travel> info = travelRepository.findById(Long.valueOf(travelId));
        return TravelCreateDto.HomeView.fromEntity(info.get());
    }
    @Transactional
    public void deleteTravel(Travel travel){
        travelRepository.delete(travel);
    }
}
