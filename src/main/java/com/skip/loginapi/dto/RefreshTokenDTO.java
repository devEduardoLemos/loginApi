package com.skip.loginapi.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenDTO {

    public class TokenRefreshRequest{

        @NotBlank
        private String refreshToken;

        public @NotBlank String getRefreshToken() {
            return refreshToken;
        }
    }

    public static class TokenRefreshResponse{
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";

        public TokenRefreshResponse(String accessToken, String refreshToken){
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }


}
