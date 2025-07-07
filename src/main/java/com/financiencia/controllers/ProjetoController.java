package com.financiencia.controllers;

import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.entities.Universidade;
import com.financiencia.repositories.CidadeRepository;
import com.financiencia.repositories.UniversidadeRepository;
import com.financiencia.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> listarprojetos(
            // O Spring injeta automaticamente os parâmetros da URL (?page=X&size=Y&sort=Z)
            // em um objeto Pageable.
            // @PageableDefault define os valores padrão se o frontend não os enviar.
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        try {
            // Chama o serviço atualizado
            Page<Projeto> projetosPaginados = projetoService.listarProjetos(pageable);
            // Retorna a página completa. O Spring/Jackson irá serializar isso
            // para o JSON que o frontend espera (com "content", "totalPages", etc.)
            return ResponseEntity.ok().body(projetosPaginados);
        } catch (Exception e) {
            // Mantive seu tratamento de erro, mas você pode querer ser mais específico
            return ResponseEntity.badRequest().body("Erro ao listar projetos! " + e.getMessage());
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
