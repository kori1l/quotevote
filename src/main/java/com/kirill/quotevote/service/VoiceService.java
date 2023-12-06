package com.kirill.quotevote.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.kirill.quotevote.dto.VoiceDto;
import com.kirill.quotevote.entity.User;
import com.kirill.quotevote.entity.Voice;
import com.kirill.quotevote.enums.VoiceTypeEnum;
import com.kirill.quotevote.repository.VoiceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoiceService {
    private final VoiceRepository voiceRepository;

    public Voice create(VoiceTypeEnum voiceType, User user) {
        Voice voice = Voice.builder()
                .voiceType(voiceType)
                .creationDate(LocalDate.now())
                .user(user)
                .build();
        return voiceRepository.save(voice);
    }

    public List<VoiceDto> toDtoList(List<Voice> voices) {
        return voices.stream().map(this::toDto).collect(Collectors.toList());
    }

    public VoiceDto toDto(Voice voice) {
        return VoiceDto.builder().voiceType(voice.getVoiceType()).creationDate(voice.getCreationDate()).build();
    }

}
