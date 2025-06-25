package com.financiencia.controllers;
import com.financiencia.entities.Cidade;
import com.financiencia.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarCidades() {
        try {
            List<Cidade> listaCidadesRetorno = cidadeService.listarCidades();
            return ResponseEntity.ok(listaCidadesRetorno);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    }

