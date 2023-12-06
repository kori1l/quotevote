package com.kirill.quotevote.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kirill.quotevote.enums.VoiceTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddVoiceRequestDto {
    private Long userId;
    private Long quoteId;
    private VoiceTypeEnum voiceType;
}
