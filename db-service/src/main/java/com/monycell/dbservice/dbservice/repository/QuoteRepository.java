package com.monycell.dbservice.dbservice.repository;

import com.monycell.dbservice.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
     List<Quote> findByUsername(String username);
}
