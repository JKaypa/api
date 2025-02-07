package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.UserRequestDto;
import med.voll.api.domain.users.Users;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid UserRequestDto user){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                user.username(),
                user.password()
        );

        var authenticatedUser = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generateToken((Users) authenticatedUser.getPrincipal());

        return ResponseEntity.ok(jwtToken);
    }
}
