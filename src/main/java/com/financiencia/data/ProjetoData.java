package com.financiencia.data;

import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.entities.Universidade;
import com.financiencia.exceptions.RecursoNaoEncontradoException;
import com.financiencia.repositories.CidadeRepository;
import com.financiencia.repositories.ProjetoRepository;
import com.financiencia.repositories.UniversidadeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@DependsOn({"universidadeData", "cidadeData"})
@Component
public class ProjetoData {

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    private final ProjetoRepository projetoRepository;

    public ProjetoData(ProjetoRepository projetoRepository){
        this.projetoRepository = projetoRepository;
    }

    @PostConstruct
    public void LocalProjetoData(){

        if (projetoRepository.count() == 0){

            List<Projeto> projetos = List.of(

                    new Projeto("Energia Solar Sustentável",
                                "Pesquisa sobre novos materiais para painéis solares.",
                                "Ana Souza, Carlos Lima",
                                "ana.souza@email.com",
                                universidadeRepository.findById(1L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(1L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                                ),

                    new Projeto("Inteligência Artificial na Saúde",
                                "Uso de IA para diagnóstico precoce de doenças.",
                                "Mariana Alves, João Pedro",
                                "amariana.alves@email.com",
                                universidadeRepository.findById(2L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(2L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
            ),

                    new Projeto("Eficiência Energética na Indústria",
                                "Estudo sobre otimização do consumo de energia.",
                                "Fernanda Ribeiro, Diego Costa",
                                "fernanda.ribeiro@email.com",
                                universidadeRepository.findById(3L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(3L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
            ),
                    new Projeto("Nanotecnologia na Agricultura",
                                "Uso de nanopartículas para fertilizantes inteligentes.",
                                "ALucas Martins, Beatriz Silva",
                                "lucas.martins@email.com",
                                universidadeRepository.findById(4L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(4L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
            ),
                    new Projeto("Robótica Educacional",
                                "Desenvolvimento de kits para ensino de robótica.",
                                "Roberta Santos, Bruno Rocha",
                                "roberta.santos@email.com",
                                universidadeRepository.findById(5L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(5L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
            ),
                    new Projeto("Blockchain para Segurança Digital",
                                "Uso de blockchain para proteção de dados.",
                                "Vinícius Mendes, Julia Ferreira",
                                "vinicius.mendes@email.com",
                                universidadeRepository.findById(6L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(6L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                    ),
                    new Projeto("Veículos Autônomos",
                                "Sistema de navegação inteligente para veículos autônomos.",
                                "Gustavo Oliveira, Larissa Cunha",
                                "gustavo.oliveira@email.com",
                                universidadeRepository.findById(7L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(7L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                    ),
                    new Projeto("Saneamento Inteligente",
                                "Tecnologia para otimização do tratamento de esgoto.",
                                "Thiago Nascimento, Camila Lopes",
                                "thiago.nascimento@email.com",
                                universidadeRepository.findById(8L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(8L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                    ),
                    new Projeto("Realidade Aumentada na Educação",
                                "Aplicação de RA para ensino interativo.",
                                "Patrícia Duarte, Rafael Cardoso",
                                "patricia.duarte@email.com",
                                universidadeRepository.findById(9L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(9L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                    ),
                    new Projeto("Cidades Inteligentes",
                                "Sensores IoT para gestão urbana eficiente.",
                                "Matheus Xavier, Sofia Pereira",
                                "matheus.xavier@email.com",
                                universidadeRepository.findById(10L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada!")),
                                cidadeRepository.findById(10L)
                                        .orElseThrow(()->new RecursoNaoEncontradoException("Cidade não encontrada!"))
                    )

            );

            projetoRepository.saveAll(projetos);

        }
    }
}
