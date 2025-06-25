package com.financiencia.controllers;

import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.entities.Universidade;
import com.financiencia.repositories.CidadeRepository;
import com.financiencia.repositories.ProjetoRepository;
import com.financiencia.repositories.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Projeto>> listarprojetos() {
        List<Projeto> listagemProjetos = projetoRepository.findAll(Sort.by("id").ascending());
        return ResponseEntity.ok().body(listagemProjetos);
    }

    @GetMapping("/visualizar/{id}")
    public ResponseEntity<Optional<Projeto>> visualizarProjeto(@PathVariable Long id) {
        Optional<Projeto> visualizarProjeto = projetoRepository.findById(id);
        if (visualizarProjeto.isPresent()) {
            return ResponseEntity.ok().body(visualizarProjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Projeto>> buscarProjeto(@RequestParam String tituloProjeto) {
        List<Projeto> buscarProjeto = projetoRepository.findBytituloProjetoContainingIgnoreCase(tituloProjeto);
        if (!buscarProjeto.isEmpty()) {
            return ResponseEntity.ok().body(buscarProjeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoProjeto(@RequestBody Projeto projeto) {
        if (projeto.getTituloProjeto() == null || projeto.getTituloProjeto().isEmpty()) {
            return ResponseEntity.badRequest().body("O título do projeto não pode ser nulo!");
        } else if (projeto.getDescricaoProjeto() == null || projeto.getDescricaoProjeto().isEmpty()) {
            return ResponseEntity.badRequest().body("A descrição do projeto não pode ser vazia!");
        } else if (projeto.getAlunos() == null || projeto.getAlunos().isEmpty()) {
            return ResponseEntity.badRequest().body("Ao menos um aluno deve ser designado para o projeto!");
        } else if (projeto.getEmail() == null || projeto.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Informe o e-mail para contato");
        } else if (projeto.getCidade() == null) {
            return ResponseEntity.badRequest().body("Cidade Inválida");
        } else if (projeto.getUniversidade() == null) {
            return ResponseEntity.badRequest().body("Universidade Inválida");
        }

        Cidade cidade = cidadeRepository.findById(projeto.getCidade().getId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Universidade universidade = universidadeRepository.findById(projeto.getUniversidade().getId())
                .orElseThrow(() -> new RuntimeException("Universidade não encontrada"));

        projeto.setCidade(cidade);
        projeto.setUniversidade(universidade);

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return ResponseEntity.ok().body(projetoSalvo);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Optional<Projeto>> editarProjeto(@RequestBody Projeto projeto, @PathVariable Long id) {
        Optional<Projeto> retorno = projetoRepository.findById(id).map(record -> {
            if (projeto.getTituloProjeto() != null && !projeto.getTituloProjeto().isEmpty()) {
                record.setTituloProjeto(projeto.getTituloProjeto());
            }
            if (projeto.getDescricaoProjeto() != null && !projeto.getDescricaoProjeto().isEmpty()) {
                record.setDescricaoProjeto(projeto.getDescricaoProjeto());
            }
            if (projeto.getAlunos() != null && !projeto.getAlunos().isEmpty()) {
                record.setAlunos(projeto.getAlunos());
            }
            if (projeto.getEmail() != null && !projeto.getEmail().isEmpty()) {
                record.setEmail(projeto.getEmail());
            }
            if (projeto.getCidade() != null) {
                Cidade cidade = cidadeRepository.findById(projeto.getCidade().getId())
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
                record.setCidade(cidade);
            }
            if (projeto.getUniversidade() != null) {
                Universidade universidade = universidadeRepository.findById(projeto.getUniversidade().getId())
                        .orElseThrow(() -> new RuntimeException("Universidade não encontrada"));
                record.setUniversidade(universidade);
            }
            return projetoRepository.save(record);
        });
        return ResponseEntity.ok().body(retorno);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarProjeto(@PathVariable Long id) {
        return projetoRepository.findById(id)
                .map(record -> {
                    projetoRepository.deleteById(id);
                    return ResponseEntity.ok().body("Projeto deletado com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
