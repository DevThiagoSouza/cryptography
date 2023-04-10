package com.engenharia_software_moderna.entities.dto;

import com.engenharia_software_moderna.entities.model.LoginModel;
import lombok.Data;

@Data
public class LoginDTO {
    private Integer id;
    private String email;
    private String password;

    public LoginDTO(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public LoginDTO() {}

    public LoginModel transformToObject(){
        return new LoginModel(id,email,password);
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
