package com.financiencia.service;

import com.financiencia.entities.Cidade;
import com.financiencia.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listarCidades(){

    List<Cidade> cidadesRetorno = cidadeRepository.findAll(Sort.by("id").ascending());

    if (cidadesRetorno.isEmpty()){
        throw new RuntimeException("A lista está vazia");
    }

    return cidadesRetorno;
    }





}
