package br.futurodev.joiville.acoessustentaveis.meioambiente.controladores;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Organizacao;
import br.futurodev.joiville.acoessustentaveis.meioambiente.servicos.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    public List<Organizacao> getAll() {
        return organizacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> getById(@PathVariable Long id) {
        return organizacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Organizacao create(@RequestBody Organizacao organizacao) {
        return organizacaoService.save(organizacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> update(@PathVariable Long id, @RequestBody Organizacao organizacao) {
        return organizacaoService.findById(id)
                .map(existingOrganizacao -> {
                    existingOrganizacao.setNome(organizacao.getNome());
                    existingOrganizacao.setContato(organizacao.getContato());
                    return ResponseEntity.ok(organizacaoService.save(existingOrganizacao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
