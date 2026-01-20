package com.next.routeMaster.authDto;


import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
     @NotBlank String password){
}

