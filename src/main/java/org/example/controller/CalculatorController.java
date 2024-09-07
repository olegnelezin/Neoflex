package org.example.controller;

import org.example.dto.request.CalculateRequest;
import org.example.dto.response.CalculateResponse;
import org.example.model.SuccessModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/api")
@RestController
public class CalculatorController {

    @GetMapping("/calculate")
    SuccessModel<CalculateResponse> getVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int countOfDays,
            @RequestParam(required = false) LocalDate startVacationDate) {
        var request = CalculateRequest.builder()
                .averageSalary(averageSalary)
                .countOfDays(countOfDays)
                .startVacationDate(startVacationDate);
        var response = new CalculateResponse(12);
        return SuccessModel.okSuccessModel(response, "Successfully calculated");
    }
}
