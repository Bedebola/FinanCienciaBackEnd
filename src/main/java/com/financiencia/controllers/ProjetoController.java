package com.financiencia.controllers;

import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.entities.Universidade;
import com.financiencia.repositories.CidadeRepository;
import com.financiencia.repositories.UniversidadeRepository;
import com.financiencia.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProjetoService projetoService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarprojetos() {
        try {
            List<Projeto> projetos= projetoService.listarProjetos();
            return ResponseEntity.ok().body(projetos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar projetos!" + e);
        }
    }

    @GetMapping("/visualizar/{id}")
    public ResponseEntity<?> visualizarProjeto(@PathVariable Long id) {
        try {
            Projeto projetoId= projetoService.visualizarId(id);
            return ResponseEntity.ok().body(projetoId);
        } catch (Exception e) {
            throw new RuntimeException("nenhum projeto encontrado com o ID informado!" + e);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProjeto(@RequestParam String tituloProjeto) {
        try {
            List<Projeto> projetoTitulo= projetoService.buscarProjeto(tituloProjeto);
            return ResponseEntity.ok().body(projetoTitulo);
        } catch (Exception e) {
            throw new RuntimeException("nenhum projeto encontrado com o título informado!" + e);
        }

    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoProjeto(@RequestBody Projeto projeto) {






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
