
package com.financiencia.data;

import com.financiencia.entities.Cidade;
import com.financiencia.repositories.CidadeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeData {

    private final CidadeRepository cidadeRepository;

    public CidadeData(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @PostConstruct

    public void LoadCidadeData (){

        if (cidadeRepository.count() == 0) {

            List<Cidade> cidades = List.of(

            new Cidade("Araranguá"),
            new Cidade("Criciúma"),
            new Cidade("Forquilhinha"),
            new Cidade("Içara"),
            new Cidade("Tubarão"),
            new Cidade("Laguna"),
            new Cidade("Braço do Norte"),
            new Cidade("Orleans"),
            new Cidade("São Ludgero"),
            new Cidade("Imbituba")
            );
        cidadeRepository.saveAll(cidades);

        }
    }
}
