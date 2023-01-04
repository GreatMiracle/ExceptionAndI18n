package com.example.demo.web.rest;

import com.example.demo.service.ScheduleService;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.RestResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class SheduleResource {
    private final ScheduleService scheduleService;
    @GetMapping()
    public ResponseEntity<BodyResponseDTO<String>> testSchedule() {
        return RestResponseWrapper.getSuccess( scheduleService.testSchedule());
    }
}
