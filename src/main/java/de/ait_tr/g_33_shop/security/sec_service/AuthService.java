package de.ait_tr.g_33_shop.security.sec_service;

import de.ait_tr.g_33_shop.domain.entity.User;
import de.ait_tr.g_33_shop.security.sec_dto.TokenResponseDto;
import de.ait_tr.g_33_shop.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private UserService userService;
    private TokenService tokenService;
    private Map<String, String> refreshStorage;
    private BCryptPasswordEncoder encoder;

    public AuthService(UserService userService, TokenService tokenService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.refreshStorage = new HashMap<>();
        this.encoder = encoder;
    }

    public TokenResponseDto login(User inboundUser) throws AuthException {
        String username = inboundUser.getUsername();
        UserDetails foundUser = userService.loadUserByUsername(username);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);
        }
        throw new AuthException("Password is incorrect");
    }

    public TokenResponseDto getNewAccessToken(String inboundRefreshToken) throws AuthException {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String foundRefreshToken = refreshStorage.get(username);

        if (foundRefreshToken != null && foundRefreshToken.equals(inboundRefreshToken)) {
            UserDetails foundUser = userService.loadUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(foundUser);
            return new TokenResponseDto(accessToken, null);
        }

        throw new AuthException("Refresh token is incorrect");
    }
}