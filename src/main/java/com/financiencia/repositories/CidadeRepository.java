package com.financiencia.repositories;

import com.financiencia.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
