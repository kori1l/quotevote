package com.kirill.quotevote.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.kirill.quotevote.enums.VoiceTypeEnum;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voice {
    @Id
    @GeneratedValue
    private Long id;
    private VoiceTypeEnum voiceType;
    private LocalDate creationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quote_id")
    private Quote quote;
}
