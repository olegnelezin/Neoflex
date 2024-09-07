package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CalculateRequest;
import org.example.dto.response.CalculateResponse;
import org.example.service.CalculateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class CalculateServiceImpl implements CalculateService {

    @Override
    public CalculateResponse calculateVacation(CalculateRequest calculateRequest) {
        double averageSalary = calculateRequest.getAverageSalary();
        int countOfDays = calculateRequest.getCountOfDays();

        double dailyRate = averageSalary / 365;
        double vacationPay = dailyRate * countOfDays;

        LocalDate startDate = calculateRequest.getStartVacationDate();
        if (startDate != null) {
            vacationPay = calculateVacationWithHolidays(dailyRate, countOfDays, startDate);
        }

        log.info("Vacation has been calculated: " + vacationPay);
        return new CalculateResponse(vacationPay);
    }

    private double calculateVacationWithHolidays(double dailyRate, int days, LocalDate startDate) {
        int validVacationDays = 0;
        for (int i = 0; i < days; ++i) {
            if (!isWeekend(startDate)) {
                validVacationDays++;
            }
            startDate = startDate.plusDays(1);
        }

        return dailyRate * validVacationDays;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }
}
