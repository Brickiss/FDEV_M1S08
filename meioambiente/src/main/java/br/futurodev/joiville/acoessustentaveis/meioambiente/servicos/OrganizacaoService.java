package br.futurodev.joiville.acoessustentaveis.meioambiente.servicos;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Organizacao;
import br.futurodev.joiville.acoessustentaveis.meioambiente.repositorios.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public List<Organizacao> findAll() {
        return organizacaoRepository.findAll();
    }

    public Optional<Organizacao> findById(Long id) {
        return organizacaoRepository.findById(id);
    }

    public Organizacao save(Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }

    public void delete(Long id) {
        organizacaoRepository.deleteById(id);
    }

    public List<Organizacao> buscarOrganizacoes(String nome, String contato) {
        return organizacaoRepository.findAllOptionalFilter(nome, contato);
    }

}
