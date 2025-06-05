package com.gume.mapa_dinamico_motorlub.entrypoint.controller;

import com.gume.mapa_dinamico_motorlub.application.usecase.LoginUseCase;
import com.gume.mapa_dinamico_motorlub.entrypoint.controller.dto.LoginDto;
import com.gume.mapa_dinamico_motorlub.entrypoint.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginDto> logar(@RequestBody LoginDto loginDto) {
        LoginDto response = LoginMapper.paraDto(loginUseCase.autenticar(LoginMapper.paraDomain(loginDto)));
        return ResponseEntity.ok(response);
    }

}
