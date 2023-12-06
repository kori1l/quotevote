package com.kirill.quotevote.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kirill.quotevote.dto.QuoteDto;
import com.kirill.quotevote.entity.Quote;
import com.kirill.quotevote.entity.User;
import com.kirill.quotevote.entity.Voice;
import com.kirill.quotevote.enums.VoiceTypeEnum;
import com.kirill.quotevote.repository.QuoteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final VoiceService voiceService;

    public QuoteDto create(String content, Long userId) {
        Quote quote = Quote.builder()
                .content(content)
                .creationDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .user(userService.getUserById(userId))
                .voices(new ArrayList<>())
                .score(0L)
                .build();
        return this.toDto(quoteRepository.save(quote));
    }

    public void delete(Long quoteId) {
        Quote quote = getQuoteById(quoteId);
        quoteRepository.delete(quote);
    }

    public QuoteDto update(Long quoteId, String content) {
        Quote quote = getQuoteById(quoteId);
        quote.setContent(content);
        quote.setUpdateDate(LocalDate.now());
        return this.toDto(quote);
    }

    public QuoteDto addVoice(Long userId, Long quoteId, VoiceTypeEnum voiceType) {
        Quote quote = getQuoteById(quoteId);
        User user = userService.getUserById(userId);
        Voice voice = voiceService.create(voiceType, user);
        voice.setQuote(quote);
        changeScore(quote, voice.getVoiceType());
        quoteRepository.save(quote);
        return this.toDto(quote);
    }

    public Quote getQuoteById(Long id) {
        return quoteRepository.getReferenceById(id);
    }

    public QuoteDto getRandomQuote() {
        return this.toDto(quoteRepository.findRandomQuote());
    }

    public List<QuoteDto> getTopTenQuotes() {
        return quoteRepository.findTopTenQuotes().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<QuoteDto> getWorseTenQuotes() {
        return quoteRepository.findWorseTenQuotes().stream().map(this::toDto).collect(Collectors.toList());
    }

    public QuoteDto toDto(Quote quote) {
        return QuoteDto.builder()
                .id(quote.getId())
                .content(quote.getContent())
                .creationDate(quote.getCreationDate())
                .updateDate(quote.getUpdateDate())
                .user(userService.toDto(quote.getUser()))
                .voices(voiceService.toDtoList(quote.getVoices()))
                .score(quote.getScore())
                .build();
    }

    private void changeScore(Quote quote, VoiceTypeEnum voiceType) {
        if (voiceType.equals(VoiceTypeEnum.UP)) {
            quote.setScore(quote.getScore() + 1);
        } else {
            quote.setScore(quote.getScore() - 1);
        }
    }


}

