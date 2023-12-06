package com.kirill.quotevote.repository;

import java.util.List;

import com.kirill.quotevote.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * FROM Quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();

    @Query(value = "SELECT * FROM Quote ORDER BY score DESC LIMIT 10", nativeQuery = true)
    List<Quote> findTopTenQuotes();

    @Query(value = "SELECT * FROM Quote ORDER BY score LIMIT 10", nativeQuery = true)
    List<Quote> findWorseTenQuotes();
}
