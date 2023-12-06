package com.kirill.quotevote.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.kirill.quotevote.dto.QuoteDto;
import com.kirill.quotevote.dto.request.AddVoiceRequestDto;
import com.kirill.quotevote.dto.request.CreateQuoteRequestDto;
import com.kirill.quotevote.dto.request.UpdateQuoteRequestDto;
import com.kirill.quotevote.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/createQuote")
    public QuoteDto createQuote(@RequestBody CreateQuoteRequestDto request) {
        return quoteService.create(request.getContent(), request.getUserId());
    }

    @DeleteMapping("/deleteQuote")
    public ResponseEntity<String> deleteQuote(@RequestParam(value = "quoteId") Long quoteId) {
        quoteService.delete(quoteId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateQuote")
    public QuoteDto updateQuote(@RequestBody UpdateQuoteRequestDto request) {
        return quoteService.update(request.getQuoteId(), request.getContent());
    }

    @PostMapping("/vote")
    public QuoteDto vote(@RequestBody AddVoiceRequestDto request) {
        return quoteService.addVoice(request.getUserId(), request.getQuoteId(), request.getVoiceType());
    }

    @GetMapping("/randomQuote")
    public QuoteDto randomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/topTenQuotes")
    public List<QuoteDto> getTopTenQuotes() {
        return quoteService.getTopTenQuotes();
    }

    @GetMapping("/worseTenQuotes")
    public List<QuoteDto> getWorseTenQuotes() {
        return quoteService.getWorseTenQuotes();
    }
}
