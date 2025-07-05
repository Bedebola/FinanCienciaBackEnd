package com.financiencia.service;

import com.financiencia.dto.UsuarioRequest;
import com.financiencia.dto.UsuarioResponse;
import com.financiencia.entities.Usuario;
import com.financiencia.jwt.TokenService;
import com.financiencia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuarioResponse createUser (UsuarioRequest usuarioRequest){
        Optional<Usuario> usuarioResult =
                usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (usuarioResult.isPresent()) {
            throw new RuntimeException("Usuario já cadastrado");
        }

        if (usuarioRequest.getNome() == null || usuarioRequest.getNome().isBlank()){
            throw new RuntimeException("O campo NOME é obrigatório!");
        }

        if (usuarioRequest.getEmail() == null || usuarioRequest.getEmail().isBlank()){
            throw new RuntimeException("O campo E-MAIL é obrigatório!");
        }

        if (usuarioRequest.getSenha() == null || usuarioRequest.getSenha().isBlank()){
            throw new RuntimeException("O campo SENHA é obrigatório!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setId(null);

        Usuario usuarioPersistResult = usuarioRepository.save(usuario);

        UsuarioResponse retorno = new UsuarioResponse();
        retorno.setId(usuarioPersistResult.getId());

        return retorno;
    }

    public UsuarioResponse login (UsuarioRequest usuario) throws Exception {
        Optional<Usuario> usuarioResult =
                usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioResult.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado!");
        }

        Usuario usuarioRecord= usuarioResult.get();

        if(passwordEncoder.matches(usuario.getSenha(), usuarioRecord.getSenha())) {
            UsuarioResponse response = new UsuarioResponse();
            response.setId(usuarioRecord.getId());
            response.setEmail(usuarioRecord.getEmail());
            response.setToken(tokenService.generateToken(usuarioRecord));

            return response;
        }

        throw new RuntimeException("Senha invalida");

    }




}
