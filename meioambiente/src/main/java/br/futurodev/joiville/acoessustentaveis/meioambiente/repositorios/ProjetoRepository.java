package br.futurodev.joiville.acoessustentaveis.meioambiente.repositorios;

import br.futurodev.joiville.acoessustentaveis.meioambiente.entidades.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    @Query("SELECT p FROM Projeto p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:contato IS NULL OR LOWER(p.contato) LIKE LOWER(CONCAT('%', :contato, '%')))")
    List<Projeto> findAllOptionalFilter(@Param("nome") String nome, @Param("contato") String contato);

}
