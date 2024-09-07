package org.example.service;

import org.example.dto.request.CalculateRequest;
import org.example.dto.response.CalculateResponse;

public interface CalculateService {

    CalculateResponse calculateVacation(CalculateRequest calculateRequest);
}
