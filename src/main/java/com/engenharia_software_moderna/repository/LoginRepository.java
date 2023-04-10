package com.engenharia_software_moderna.repository;

import com.engenharia_software_moderna.entities.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Integer> {

    @Transactional
    @Query(value = "UPDATE Login SET email=:email, password=:password WHARE id=:id " ,nativeQuery = true)
    void CripyLogin(@Param("id") Integer id,
                    @Param("email") String email,
                    @Param("password") String password);
}
