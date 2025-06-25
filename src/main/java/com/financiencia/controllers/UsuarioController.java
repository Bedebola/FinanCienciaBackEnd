package com.financiencia.controllers;

import com.financiencia.dto.UsuarioRequest;
import com.financiencia.dto.UsuarioResponse;
import com.financiencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuariosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(
            @RequestBody UsuarioRequest usuario) {
        try {
            return ResponseEntity.ok(usuariosService.createUser(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(
            @RequestBody UsuarioRequest usuario) {
        try {
            return ResponseEntity.ok().body(usuariosService.login(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
