package com.financiencia.controllers;

import com.financiencia.entities.Universidade;
import com.financiencia.service.UniversidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/universidades")
public class UniversidadeController {

    @Autowired
    private UniversidadeService universidadeService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarUniversidades() {
        try {
            List<Universidade> listaUniversidadesRetorno = universidadeService.listarUniversidades();
            return ResponseEntity.ok(listaUniversidadesRetorno);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
