import org.example.dto.request.CalculateRequest;
import org.example.dto.response.CalculateResponse;
import org.example.service.impl.CalculateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateServiceTest {

    private CalculateServiceImpl calculateService;

    @BeforeEach
    public void setup() {
        calculateService = new CalculateServiceImpl();
    }

    @Test
    public void testCalculateVacationWithoutHolidays() {
        double averageSalary = 365000.0;
        int countOfDays = 10;

        CalculateRequest request = CalculateRequest.builder()
                .averageSalary(averageSalary)
                .countOfDays(countOfDays)
                .build();

        CalculateResponse response = calculateService.calculateVacation(request);

        double expectedVacationPay = (averageSalary / 365) * countOfDays;
        assertEquals(expectedVacationPay, response.getVacationPay(), 0.001);
    }

    @Test
    public void testCalculateVacationWithHolidaysNoWeekends() {

        double averageSalary = 365000.0;
        int countOfDays = 5;
        LocalDate startDate = LocalDate.of(2024, 1, 1);

        CalculateRequest request = CalculateRequest.builder()
                .averageSalary(averageSalary)
                .countOfDays(countOfDays)
                .startVacationDate(startDate)
                .build();

        CalculateResponse response = calculateService.calculateVacation(request);

        double expectedVacationPay = (averageSalary / 365) * countOfDays;
        assertEquals(expectedVacationPay, response.getVacationPay(), 0.001);
    }

    @Test
    public void testCalculateVacationWithHolidaysWithWeekends() {
        double averageSalary = 365000.0;
        int countOfDays = 7;
        LocalDate startDate = LocalDate.of(2024, 1, 5);

        CalculateRequest request = CalculateRequest.builder()
                .averageSalary(averageSalary)
                .countOfDays(countOfDays)
                .startVacationDate(startDate)
                .build();

        CalculateResponse response = calculateService.calculateVacation(request);

        int validVacationDays = 5;
        double expectedVacationPay = (averageSalary / 365) * validVacationDays;
        assertEquals(expectedVacationPay, response.getVacationPay(), 0.001);
    }
}
