
package br.futurodev.joiville.acoessustentaveis.meioambiente.controladores;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Projeto;
import br.futurodev.joiville.acoessustentaveis.meioambiente.servicos.ProjetoService;
import br.futurodev.joiville.acoessustentaveis.meioambiente.servicos.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    public List<Projeto> getAll() {
        return projetoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projeto> create(@RequestBody Projeto projeto) {
        // Verifica se a organização existe
        if (!organizacaoService.findById(projeto.getOrganizacao().getId()).isPresent()) {
            return ResponseEntity.badRequest().body(null); // Retorna erro se a organização não existir
        }
        Projeto savedProjeto = projetoService.save(projeto);
        return ResponseEntity.ok(savedProjeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> update(@PathVariable Long id, @RequestBody Projeto projeto) {
        return projetoService.findById(id)
                .map(existingProjeto -> {
                    existingProjeto.setNome(projeto.getNome());
                    existingProjeto.setDescricao(projeto.getDescricao());
                    existingProjeto.setRegiao(projeto.getRegiao());
                    existingProjeto.setEstimativaCo2(projeto.getEstimativaCo2());
                    existingProjeto.setOrganizacao(projeto.getOrganizacao()); // Atualiza a organização
                    return ResponseEntity.ok(projetoService.save(existingProjeto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
