package com.engenharia_software_moderna.repository;

import com.engenharia_software_moderna.entities.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE tb_cliente " +
            "SET nome=:nome, sobre_nome=:sobrenome, cpf=:cpf, rua=:rua, numero=:numero, " +
            "complemneto=:complemento, telefone=:telefone, cep=:cep, cidade=:cidade,uf=:uf WHERE id=:id", nativeQuery = true)
    void updateCliente(@Param("id") Integer id,
                       @Param("nome") String nome,
                       @Param("sobrenome") String sobrenome,
                       @Param("cpf") Integer cpf,
                       @Param("rua") String rua,
                       @Param("numero") Integer numero,
                       @Param("complemento") String complemento,
                       @Param("telefone") Integer telefone,
                       @Param("cep") Integer cep,
                       @Param("cidade") String cidade,
                       @Param("uf") String uf);

}
