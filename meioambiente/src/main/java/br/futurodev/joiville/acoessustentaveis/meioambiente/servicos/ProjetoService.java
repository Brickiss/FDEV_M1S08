package br.futurodev.joiville.acoessustentaveis.meioambiente.servicos;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Organizacao;
import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Projeto;
import br.futurodev.joiville.acoessustentaveis.meioambiente.repositorios.OrganizacaoRepository;
import br.futurodev.joiville.acoessustentaveis.meioambiente.repositorios.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void delete(Long id) {
        projetoRepository.deleteById(id);
    }
    @Autowired
    private OrganizacaoRepository OrganizacaoRepository;

    @Autowired
    private ProjetoRepository ProjetoRepository;

    public List<Projeto> buscarProjetos(String nome, String contato) {
        return projetoRepository.findAllOptionalFilter(nome, contato);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }
}
