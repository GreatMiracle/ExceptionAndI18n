package com.example.demo.client;

import com.example.demo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Currently the url is hardcoding url of internal batch service on the server, this url can't be accessed from local.
 * To access Batch service from local, change spring active profile to local: SPRING_PROFILES_ACTIVE=local
 * it will use the url from the application-local.yml properties file that can be accessed from local.
 */
@FeignClient(name = "image-service", url = "${image-service.url}", configuration = FeignConfiguration.class)
public interface ServiceFeignClient {
//
//    @PostMapping("/api/v1/calc/inward-policy")
//    BodyResponseDTO<CalcResultDTO> calculateIPolicy(
//        @RequestBody CalculateWithEmailsDTO calculateDTO);
//
//    @GetMapping("/api/v1/cache/reload")
//    ResponseEntity<BodyResponseDTO<CalcResultDTO>> reloadCache();

}
