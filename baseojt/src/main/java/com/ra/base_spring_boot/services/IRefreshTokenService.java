package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.exception.CustomException;
import com.ra.base_spring_boot.model.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService
{
    RefreshToken createRefreshToken(String username) throws CustomException;

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    Optional<RefreshToken> findByUserId(Long userId);
}
