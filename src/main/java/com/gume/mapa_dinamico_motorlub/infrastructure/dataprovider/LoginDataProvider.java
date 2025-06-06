package com.gume.mapa_dinamico_motorlub.infrastructure.dataprovider;

import com.gume.mapa_dinamico_motorlub.application.gateways.LoginGateway;
import com.gume.mapa_dinamico_motorlub.infrastructure.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginDataProvider implements LoginGateway {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }
}
