package com.engenharia_software_moderna.service;


import com.engenharia_software_moderna.controller.LoginController;
import com.engenharia_software_moderna.entities.dto.LoginDTO;
import com.engenharia_software_moderna.entities.model.FornecedorModel;
import com.engenharia_software_moderna.entities.model.LoginModel;
import com.engenharia_software_moderna.controller.ThirteenController;
import com.engenharia_software_moderna.entities.dto.ClienteDTO;
import com.engenharia_software_moderna.entities.dto.FornecedorDTO;
import com.engenharia_software_moderna.entities.model.ClienteModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ThirteenServiceTest {

    @InjectMocks
    private ThirteenController thirteenController;
    @InjectMocks
    private LoginController loginController;
    @Mock
    private ThirteenService thirteenService;
    @Mock
    private LoginService loginService;

    @Test
    void shouldGetAllCliente() {
        Pageable pageable = Pageable.ofSize(1);
        Page<ClienteModel> clienteModels = Mockito.spy(Page.class);

        ResponseEntity<Page<ClienteModel>> responseMock = ResponseEntity.status(HttpStatus.OK).body(clienteModels);

        Mockito.when(thirteenService.findAllCliente(pageable))
                .thenReturn(clienteModels);

        ResponseEntity<Page<ClienteModel>> responseEntity = thirteenController.findAllCliente(pageable);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldGetAllForn() {
        Pageable pageable = Pageable.ofSize(1);
        Page<FornecedorModel> fornecedorModels = Mockito.spy(Page.class);

        ResponseEntity<Page<FornecedorModel>> responseMock = ResponseEntity.status(HttpStatus.OK).body(fornecedorModels);

        Mockito.when(thirteenService.findAllFornecedor(pageable))
                .thenReturn(fornecedorModels);

        ResponseEntity<Page<FornecedorModel>> responseEntity = thirteenController.findAllFornecedor(pageable);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldGetAndReturnByIdCliente() {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(1);

        ResponseEntity<Optional<ClienteModel>> responseMock = ResponseEntity.status(HttpStatus.OK).body(Optional.of(clienteModel));

        Mockito.when(thirteenController.findbyIdCliente(clienteModel.getId()))
                .thenReturn(responseMock.getBody());

        Optional<ClienteModel> responseEntity = thirteenController.findbyIdCliente(1);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getClass());
    }

    @Test
    void shouldGetAndReturnByIdForn() {
        FornecedorModel fornecedorModel = new FornecedorModel();
        fornecedorModel.setId(1);

        ResponseEntity<Optional<FornecedorModel>> responseMock = ResponseEntity.status(HttpStatus.OK).body(Optional.of(fornecedorModel));

        Mockito.when(thirteenController.findbyIdFornecedor(fornecedorModel.getId()))
                .thenReturn(responseMock.getBody());

        Optional<FornecedorModel> responseEntity = thirteenController.findbyIdFornecedor(1);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getClass());
    }

    @Test
    void shouldGetAndReturnById() {
        LoginModel loginModel = new LoginModel();
        loginModel.setId(1);

        ResponseEntity<Optional<LoginModel>> responseMock = ResponseEntity.status(HttpStatus.OK).body(Optional.of(loginModel));

        Mockito.when(loginController.searchId(loginModel.getId()))
                .thenReturn(responseMock);

        Optional<LoginModel> responseEntity = loginController.searchId(1).getBody();

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getClass());
    }


    @Test
    void shouldCreateUsercliente() {
        ClienteDTO clienteDTO = new ClienteDTO();

        ResponseEntity<ClienteDTO> responseMock = ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);

        Mockito.when(thirteenService.createCliente(clienteDTO))
                .thenReturn(responseMock);

        ResponseEntity<ClienteDTO> responseEntity = thirteenController.createCLiente(clienteDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldCreateUserForn() {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        ResponseEntity<FornecedorDTO> responseMock = ResponseEntity.status(HttpStatus.CREATED).body(fornecedorDTO);

        Mockito.when(thirteenService.createFornecedor(fornecedorDTO))
                .thenReturn(responseMock);

        ResponseEntity<FornecedorDTO> responseEntity = thirteenController.createFornecedor(fornecedorDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }
    @Test
    void shouldCreateUser() {
        LoginDTO loginDTO = new LoginDTO();

        ResponseEntity<LoginDTO> responseMock = ResponseEntity.status(HttpStatus.CREATED).body(loginDTO);

        Mockito.when(loginService.createLogin(loginDTO))
                .thenReturn(responseMock);

        ResponseEntity<LoginDTO> responseEntity = loginController.create(loginDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldUpdateUserCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();

        ResponseEntity<ClienteDTO> responseMock = ResponseEntity.status(HttpStatus.OK).body(clienteDTO);

        Mockito.when(thirteenService.updateClt(clienteDTO))
                .thenReturn(responseMock);

        ResponseEntity<ClienteDTO> responseEntity = thirteenController.updateCliente(clienteDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldUpdateUserForn() {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        ResponseEntity<FornecedorDTO> responseMock = ResponseEntity.status(HttpStatus.OK).body(fornecedorDTO);

        Mockito.when(thirteenService.updateFornecedor(fornecedorDTO))
                .thenReturn(responseMock);

        ResponseEntity<FornecedorDTO> responseEntity = thirteenController.updateFornecedor(fornecedorDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldUpdateUser() {
        LoginDTO loginDTO = new LoginDTO();

        ResponseEntity<LoginDTO> responseMock = ResponseEntity.status(HttpStatus.OK).body(loginDTO);

        Mockito.when(loginService.updateLogin(loginDTO))
                .thenReturn(responseMock);

        ResponseEntity<LoginDTO> responseEntity = loginController.update(loginDTO);

        Assertions.assertEquals(responseMock.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldDeleteUserCLiente() {
         ClienteModel  clienteModel = new ClienteModel();

        ResponseEntity<ClienteModel> response = ResponseEntity.status(HttpStatus.OK).body(clienteModel);

        Mockito.when(thirteenService.deleteCliente(1))
                .thenReturn(response);

        ResponseEntity<ClienteModel> responseEntity = thirteenController.deleteCliente(1);

        Assertions.assertEquals(response.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldDeleteUserForn() {
        FornecedorModel  fornecedorModel = new FornecedorModel();

        ResponseEntity<FornecedorModel> response = ResponseEntity.status(HttpStatus.OK).body(fornecedorModel);

        Mockito.when(thirteenService.deleteFornecedor(1))
                .thenReturn(response);

        ResponseEntity<FornecedorModel> responseEntity = thirteenController.deleteFornecedor(1);

        Assertions.assertEquals(response.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void shouldDeleteUser() {
        LoginModel  loginModel = new LoginModel();

        ResponseEntity<LoginModel> response = ResponseEntity.status(HttpStatus.OK).body(loginModel);

        Mockito.when(loginService.deletelogin(1))
                .thenReturn(response);

        ResponseEntity<LoginModel> responseEntity = loginController.delete(1);

        Assertions.assertEquals(response.getStatusCode(), responseEntity.getStatusCode());
    }



}
