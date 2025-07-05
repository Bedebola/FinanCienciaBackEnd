package com.financiencia.repositories;

import com.financiencia.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository <Projeto, Long> {

    List<Projeto> findBytituloProjetoContainingIgnoreCase(String tituloProjeto);


}
