package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.exception.CustomException;
import com.ra.base_spring_boot.model.RefreshToken;
import com.ra.base_spring_boot.repository.IRefreshTokenRepository;
import com.ra.base_spring_boot.repository.IUserRepository;
import com.ra.base_spring_boot.services.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService
{
    private final IRefreshTokenRepository refreshTokenRepository;
    private final IUserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String username) throws CustomException
    {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUsername(username).orElseThrow(() -> new CustomException(username + " not found", HttpStatus.NOT_FOUND)))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token)
    {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token)
    {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0)
        {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    @Override
    public Optional<RefreshToken> findByUserId(Long userId)
    {
        return refreshTokenRepository.findByUserId(userId);
    }
}
