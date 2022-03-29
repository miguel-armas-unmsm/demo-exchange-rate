package com.tcs.exchangerate.repository;

import com.tcs.exchangerate.model.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {

  Optional<ExchangeRate> findByType(String type);

  List<ExchangeRate> findAll();
}
