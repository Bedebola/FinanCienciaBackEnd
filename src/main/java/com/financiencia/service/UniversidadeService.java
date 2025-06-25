package com.financiencia.service;

import com.financiencia.entities.Universidade;
import com.financiencia.repositories.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversidadeService {

    @Autowired
    private UniversidadeRepository universidadeRepository;

    public List<Universidade> listarUniversidades(){

        List<Universidade> universidadesRetorno = universidadeRepository.findAll(Sort.by("id").ascending());

        if (universidadesRetorno.isEmpty()){
            throw new RuntimeException("A lista está vazia");
        }

        return universidadesRetorno;

    }










}
