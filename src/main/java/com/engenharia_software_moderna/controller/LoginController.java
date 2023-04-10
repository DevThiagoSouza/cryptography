package com.engenharia_software_moderna.controller;


import com.engenharia_software_moderna.entities.model.LoginModel;
import com.engenharia_software_moderna.config.LogConfig;
import com.engenharia_software_moderna.entities.dto.LoginDTO;
import com.engenharia_software_moderna.exceptions.BadRequestException;
import com.engenharia_software_moderna.service.LoginService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final LogConfig logConfig = new LogConfig();

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Busca pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<LoginModel>> searchId(@PathVariable("id") @NotNull Integer id ) throws BadRequestException{
        logConfig.setInfoLogging("(GET) => search for id");
        return loginService.findById(id);

    }

    @Operation(summary = "Cria um novo login")
    @PostMapping("/create")
    public ResponseEntity<LoginDTO> create(@RequestBody LoginDTO loginDTO) throws BadRequestException {
        logConfig.setInfoLogging("(POST) => Create a new Login");
        return loginService.createLogin(loginDTO);
    }


    @Operation(summary = "Atualiza o Login do usuario")
    @PutMapping("/update")
    public ResponseEntity<LoginDTO> update(@RequestBody LoginDTO loginDTO) throws BadRequestException{
        logConfig.setInfoLogging("(PUT) => Update the Login");
        return loginService.updateLogin(loginDTO);
    }

    @Operation(summary = "Autentica a senha do usuario com a senha que esta no banco")
    @GetMapping("/auth")
    public ResponseEntity<Boolean> auth(@RequestBody LoginDTO loginDTO ){
        logConfig.setInfoLogging("(Get) => auth the Login");
        return loginService.authenticateLogin(loginDTO);
    }

    @Operation(summary = "Deleta o fornecedor")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<LoginModel> delete(@PathVariable("id") @NotNull Integer id) throws BadRequestException{
        logConfig.setInfoLogging("(DELETE) => /api/v1/service - delete a service");
        return loginService.deletelogin(id);
    }

}
