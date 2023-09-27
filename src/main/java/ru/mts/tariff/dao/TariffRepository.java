package ru.mts.tariff.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.tariff.model.Tariff;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Integer> {}
