package br.futurodev.joiville.acoessustentaveis.meioambiente.repositorios;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    @Query("SELECT o FROM Organizacao o WHERE " +
            "(:nome IS NULL OR LOWER(o.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:contato IS NULL OR LOWER(o.contato) LIKE LOWER(CONCAT('%', :contato, '%')))")
    List<Organizacao> findAllOptionalFilter(@Param("nome") String nome, @Param("contato") String contato);
}

