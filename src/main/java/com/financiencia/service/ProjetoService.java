package com.financiencia.service;

import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.entities.Universidade;
import com.financiencia.exceptions.RecursoNaoEncontradoException;
import com.financiencia.exceptions.ValidacaoException;
import com.financiencia.repositories.CidadeRepository;
import com.financiencia.repositories.ProjetoRepository;
import com.financiencia.repositories.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Page<Projeto> listarProjetos (Pageable pageable){
        // O método findAll do JpaRepository já entende Pageable.
        // Ele automaticamente faz a consulta no banco com os limites (LIMIT) e deslocamentos (OFFSET) corretos.
        Page<Projeto> projetos = projetoRepository.findAll(pageable);

        if (projetos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nada consta na lista de projetos!");
        }

        return projetos;
    }

    public Projeto visualizarId (Long id){
        Projeto projetoId= projetoRepository.findById(id)
                .orElseThrow(()-> {throw new RecursoNaoEncontradoException("Nenhum projeto cadastrado com esse ID!");});

        return projetoId;
    }


    public List<Projeto> buscarProjeto (String tituloProjeto){
        List<Projeto> projetoTitulo= projetoRepository.findBytituloProjetoContainingIgnoreCase(tituloProjeto);

        if (projetoTitulo.isEmpty()){
            throw new RecursoNaoEncontradoException("Nenhum resultado encontrado para a busca!");
        }

        return projetoTitulo;
    }

    private void validarProjeto(Projeto projeto) {
        if (projeto.getTituloProjeto() == null || projeto.getTituloProjeto().isEmpty()) {
            throw new ValidacaoException("O campo título é obrigatório!");
        } else if (projeto.getDescricaoProjeto() == null || projeto.getDescricaoProjeto().isEmpty()) {
            throw new ValidacaoException("O campo descricao é obrigatório!");
        } else if (projeto.getAlunos() == null || projeto.getAlunos().isEmpty()) {
            throw new ValidacaoException("O campo alunos é obrigatório!");
        } else if (projeto.getEmail() == null || projeto.getEmail().isEmpty()) {
            throw new ValidacaoException("O campo email é obrigatório!");
        } else if (projeto.getCidade() == null) {
            throw new ValidacaoException("O campo cidade é obrigatório!");
        } else if (projeto.getUniversidade() == null) {
            throw new ValidacaoException("O campo universidade é obrigatório!");
        }

        Cidade cidade = cidadeRepository.findById(projeto.getCidade().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada"));

        Universidade universidade = universidadeRepository.findById(projeto.getUniversidade().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Universidade não encontrada"));
    }

    public Projeto cadastrarProjeto (Projeto projeto){

        validarProjeto(projeto);

        Cidade cidade = cidadeRepository.findById(projeto.getCidade().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada"));

        Universidade universidade = universidadeRepository.findById(projeto.getUniversidade().getId())
                .orElseThrow(()->new RecursoNaoEncontradoException("Universidade não encontrada"));

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return projetoSalvo;

    }

    public Projeto editarProjeto (Long id, Projeto projetoAtualizado){

        Projeto projetoExistente = projetoRepository.findById(id)
                        .orElseThrow(()->new RecursoNaoEncontradoException("projeto não encontrado!"));

        validarProjeto(projetoAtualizado);

        projetoExistente.setTituloProjeto(projetoAtualizado.getTituloProjeto());
        projetoExistente.setDescricaoProjeto(projetoAtualizado.getDescricaoProjeto());
        projetoExistente.setAlunos(projetoAtualizado.getAlunos());
        projetoExistente.setEmail(projetoAtualizado.getEmail());
        projetoExistente.setCidade(projetoAtualizado.getCidade());
        projetoExistente.setUniversidade(projetoAtualizado.getUniversidade());

        return projetoRepository.save(projetoExistente);
    }

    public void excluirProjeto (Long id){

        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(()->new RecursoNaoEncontradoException("Projeto não localizado"));

        projetoRepository.delete(projeto);

    }




}
