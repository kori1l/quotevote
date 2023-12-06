package com.kirill.quotevote.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.kirill.quotevote.enums.VoiceTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoiceDto {
    private VoiceTypeEnum voiceType;
    private LocalDate creationDate;
}
