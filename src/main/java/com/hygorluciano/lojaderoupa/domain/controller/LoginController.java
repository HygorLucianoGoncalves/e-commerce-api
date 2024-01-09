package com.hygorluciano.lojaderoupa.domain.controller;

import com.hygorluciano.lojaderoupa.domain.dto.login.LoginDto;
import com.hygorluciano.lojaderoupa.domain.dto.login.LoginResponseDto;
import com.hygorluciano.lojaderoupa.domain.model.Usuario;
import com.hygorluciano.lojaderoupa.domain.service.LoginService;
import com.hygorluciano.lojaderoupa.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping()
    private ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDto dto){
        return loginService.login(dto);
    }

}
