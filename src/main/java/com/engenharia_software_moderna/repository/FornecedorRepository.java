package com.engenharia_software_moderna.repository;

import com.engenharia_software_moderna.entities.model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FornecedorRepository  extends JpaRepository<FornecedorModel, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE tb_fornecedor " +
            "SET nome=:nome, name_fantasia=:nomeFantasia, CNPJ=:CNPJ, rua=:rua, numero=:numero, " +
            "complemento=:complemento, telefone=:telefone, cep=:cep, cidade=:cidade,uf=:uf WHERE id=:id", nativeQuery = true)
    void updateFornecedor(@Param("id") Integer id,
                       @Param("nome") String nome,
                       @Param("nomeFantasia") String nomeFantasia,
                       @Param("CNPJ") Integer CNPJ,
                       @Param("rua") String rua,
                       @Param("numero") Integer numero,
                       @Param("complemento") String complemento,
                       @Param("telefone") Integer telefone,
                       @Param("cep") Integer cep,
                       @Param("cidade") String cidade,
                       @Param("uf") String uf);
}
