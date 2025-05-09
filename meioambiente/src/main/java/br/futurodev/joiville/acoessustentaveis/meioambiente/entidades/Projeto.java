package br.futurodev.joiville.acoessustentaveis.meioambiente.entidades;

import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private String regiao;

    @Column(nullable = false)
    private Double estimativaCo2;

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organizacao organizacao;

}
