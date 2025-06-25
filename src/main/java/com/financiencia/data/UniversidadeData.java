package com.financiencia.data;

import com.financiencia.entities.Universidade;
import com.financiencia.repositories.UniversidadeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversidadeData {

    private final UniversidadeRepository universidadeRepository;

    public UniversidadeData(UniversidadeRepository universidadeRepository) {
        this.universidadeRepository = universidadeRepository;
    }

    @PostConstruct
    public void LocalUniversidadeData(){

        if(universidadeRepository.count() == 0){

            List<Universidade> universidades = List.of (

            new Universidade("Universidade Federal de Santa Catarina (UFSC)"),
            new Universidade("Universidade do Sul de Santa Catarina (UNISUL)"),
            new Universidade("Universidade do Extremo Sul Catarinense (UNESC)"),
            new Universidade("Centro Universitário Barriga Verde (UNIBAVE)"),
            new Universidade("Centro Universitário Leonardo da Vinci (UNIASSELVI)"),
            new Universidade("Faculdade SATC"),
            new Universidade("Faculdade de Tecnologia SENAI"),
            new Universidade("Instituto Federal de Santa Catarina (IFSC)"),
            new Universidade("Universidade do Estado de Santa Catarina (UDESC)"),
            new Universidade("Serviço Nacional de Aprendizagem Comercial (SENAC)")

        );

            universidadeRepository.saveAll(universidades);

        }
    }
}
