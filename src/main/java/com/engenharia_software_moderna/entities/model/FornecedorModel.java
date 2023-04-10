package com.engenharia_software_moderna.entities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_fornecedor")
public class FornecedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "CNPJ")
    private Integer cnpj;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "telefone")
    private Integer telefone;

    @Column(name = "name_fantasia")
    private String nomeFantasia;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private Integer cep;

    @Column(name = "cidade")
    private String cidade;


    @Override
    public String toString() {
        return "FornecedorModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj=" + cnpj +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", telefone=" + telefone +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", uf='" + uf + '\'' +
                ", cep=" + cep +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
