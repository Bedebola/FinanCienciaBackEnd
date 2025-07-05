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
            return ResponseEntity.badRequest().body("nenhum projeto encontrado com o título informado!" + e);
        }

    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoProjeto(@RequestBody Projeto projeto) {

    try{
        Projeto novoProjeto = projetoService.cadastrarProjeto(projeto);
        return ResponseEntity.ok().body(novoProjeto);
    }catch (Exception e){
        return ResponseEntity.badRequest().body("Não foi possível cadastrar o projeto:" + e);
    }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProjeto(@RequestBody Projeto projeto, @PathVariable Long id) {

        try {
            Projeto projetoEditado = projetoService.editarProjeto(id, projeto);
            return ResponseEntity.ok().body(projetoEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possível editar o projeto:" + e);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return ResponseEntity.noContent().build();
    }

}
