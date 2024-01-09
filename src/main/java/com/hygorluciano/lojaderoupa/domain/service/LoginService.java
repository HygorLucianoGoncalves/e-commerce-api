package com.hygorluciano.lojaderoupa.domain.service;

import com.hygorluciano.lojaderoupa.domain.dto.login.LoginDto;
import com.hygorluciano.lojaderoupa.domain.dto.login.LoginResponseDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<LoginResponseDto> login(LoginDto dto);
}
