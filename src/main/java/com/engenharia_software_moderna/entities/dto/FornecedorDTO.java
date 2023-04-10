package com.engenharia_software_moderna.entities.dto;

import com.engenharia_software_moderna.entities.model.FornecedorModel;
import lombok.Data;

@Data
public class FornecedorDTO {

    private Integer id;
    private String nome;
    private Integer cnpj;
    private String rua;
    private Integer numero;
    private String complemento;
    private Integer telefone;
    private String nomeFantasia;
    private String uf;
    private Integer cep;
    private String cidade;

    public FornecedorDTO(Integer id, String nome, Integer cnpj, String rua, Integer numero, String complemento, Integer telefone, String nomeFantasia, String uf, Integer cep, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.telefone = telefone;
        this.nomeFantasia = nomeFantasia;
        this.uf = uf;
        this.cep = cep;
        this.cidade = cidade;
    }

    public FornecedorDTO() {}

    public FornecedorModel transformToObject() {return new FornecedorModel(id,nome,cnpj,rua,numero,complemento ,telefone,nomeFantasia,uf,cep,cidade);}

    @Override
    public String toString() {
        return "FornecedorDTO{" +
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
