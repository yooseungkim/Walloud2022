package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.TravelRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class TravelService {
	private final TravelRepository travelRepository;

    @Transactional
    public TravelDto createTravel(TravelDto request) {
        Travel travel = Travel.builder()
        		//.Id(userdto.getId())
                .name(request.getName())
                .build();
        travelRepository.save(travel);
        return TravelDto.fromEntity(travel);
    }

}
