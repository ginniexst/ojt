package com.ra.base_spring_boot.controller.refresh;

import com.ra.base_spring_boot.dto.req.RefreshDTO;
import com.ra.base_spring_boot.dto.resp.JwtResponse;
import com.ra.base_spring_boot.exception.CustomException;
import com.ra.base_spring_boot.model.RefreshToken;
import com.ra.base_spring_boot.security.jwt.JwtProvider;
import com.ra.base_spring_boot.services.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/refresh")
@RequiredArgsConstructor
public class RefreshController
{
    private final IRefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;

    /**
     * @param refreshDTO RefreshDTO
     * @apiNote handle refresh token
     */
    @PostMapping
    public ResponseEntity<?> handleRefreshToken(@RequestBody RefreshDTO refreshDTO) throws CustomException
    {
        return ResponseEntity.ok().body(
                refreshTokenService.findByToken(refreshDTO.getToken())
                        .map(refreshTokenService::verifyExpiration)
                        .map(RefreshToken::getUser)
                        .map(user ->
                        {
                            String accessToken = jwtProvider.generateToken(user.getUsername());
                            return JwtResponse.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshDTO.getToken())
                                    .user(user)
                                    .roles(user.getRoles().stream().map(role -> role.getRoleName().toString()).collect(Collectors.toSet()))
                                    .build();
                        }).orElseThrow(() -> new CustomException("Refresh Token is not in DB..!!", HttpStatus.NOT_FOUND))
        );
    }

}
