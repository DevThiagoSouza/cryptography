package com.engenharia_software_moderna.entities.dto;

import com.engenharia_software_moderna.entities.model.ClienteModel;
import lombok.Data;

@Data
public class ClienteDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer cpf;
    private String rua;
    private  Integer numero;
    private String complemento;
    private Integer telefone;
    private Integer cep;
    private String cidade;
    private String uf;


    public ClienteDTO(Integer id, String nome, String sobrenome, Integer cpf, String rua, Integer numero, String complemento, Integer telefone, Integer cep, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public ClienteDTO() {}

    public ClienteModel transformToObject() {return new ClienteModel(id,nome,sobrenome,cpf,rua,numero,complemento,telefone,cep,cidade,uf);}

    @Override
    public String toString() {
        return "ClienteDTO{" +
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
