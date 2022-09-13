package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TravelController {
    private final TravelService travelservice;

    @PostMapping("/create-travel")
    public ResponseEntity<TravelDto> createTravel(@RequestBody String name) {
        TravelDto request = new TravelDto(name);
        return ResponseEntity.ok(travelservice.createTravel(request));
    }
}
