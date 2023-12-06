package com.kirill.quotevote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {
    private Long id;
    private String content;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private UserDto user;
    private List<VoiceDto> voices;
    private Long score;
}
