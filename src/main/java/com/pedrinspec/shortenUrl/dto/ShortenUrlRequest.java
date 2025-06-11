package com.pedrinspec.shortenUrl.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortenUrlRequest {

    @NotNull
    private String url;



}
