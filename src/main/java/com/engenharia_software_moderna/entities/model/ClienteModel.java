package com.engenharia_software_moderna.entities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobre_nome")
    private String sobrenome;

    @Column(name = "cpf")
    private Integer cpf;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private  Integer numero;

    @Column(name = "complemneto")
    private String complemento;

    @Column(name = "telefone")
    private Integer telefone;

    @Column(name = "cep")
    private Integer cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Override
    public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf=" + cpf +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", telefone=" + telefone +
                ", cep=" + cep +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
