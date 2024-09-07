package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.CalculateRequest;
import org.example.dto.response.CalculateResponse;
import org.example.model.SuccessModel;
import org.example.service.CalculateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CalculatorController {

    public final CalculateService calculateService;

    @GetMapping("/calculate")
    SuccessModel<CalculateResponse> getVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int countOfDays,
            @RequestParam(required = false) LocalDate startVacationDate) {
        var request = CalculateRequest.builder()
                .averageSalary(averageSalary)
                .countOfDays(countOfDays)
                .startVacationDate(startVacationDate)
                .build();

        var response = calculateService.calculateVacation(request);
        return SuccessModel.okSuccessModel(response, "Successfully calculated");
    }
}
