package com.engenharia_software_moderna.controller;
import com.engenharia_software_moderna.entities.model.FornecedorModel;
import com.engenharia_software_moderna.exceptions.BadRequestException;
import com.engenharia_software_moderna.config.LogConfig;
import com.engenharia_software_moderna.entities.dto.ClienteDTO;
import com.engenharia_software_moderna.entities.dto.FornecedorDTO;
import com.engenharia_software_moderna.entities.model.ClienteModel;
import com.engenharia_software_moderna.repository.ClienteRepository;
import com.engenharia_software_moderna.repository.FornecedorRepository;
import com.engenharia_software_moderna.service.ThirteenService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/thirteen")
public class ThirteenController {
    private final LogConfig logConfig = new LogConfig();
    private final ThirteenService thirteenService;
    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;

    public ThirteenController(ThirteenService thirteenService, ClienteRepository clienteRepository, FornecedorRepository fornecedorRepository) {
        this.thirteenService = thirteenService;
        this.clienteRepository = clienteRepository;
        this.fornecedorRepository = fornecedorRepository;
    }


    @Operation(summary = "Lista paginada de todos os clientes")
    @GetMapping("/allcliente")
    public ResponseEntity<Page<ClienteModel>> findAllCliente(Pageable pageable) throws BadRequestException {
        logConfig.setInfoLogging("GET) => /api/v1/servicos - Get all service pageable");
        Page<ClienteModel> clienteModels = clienteRepository.findAll(pageable);
        return ResponseEntity.ok(clienteModels);
    }

    @Operation(summary = "Lista paginada de todos os fornecedor")
    @GetMapping("/allfornecedor")
    public ResponseEntity<Page<FornecedorModel>> findAllFornecedor(Pageable pageable) throws BadRequestException {
        logConfig.setInfoLogging("GET) => /api/v1/servicos - Get all service pageable");
        Page<FornecedorModel> fornecedorModels = fornecedorRepository.findAll(pageable);
        return ResponseEntity.ok(fornecedorModels);
    }

    @Operation(summary = "Busca o serviço pelo ID")
    @GetMapping("/cliente/{id}")
    public Optional<ClienteModel> findbyIdCliente(@PathVariable("id") @NotNull Integer id ) throws BadRequestException{
        logConfig.setInfoLogging("(GET) => /api/v1/servicos - Get a service");
        return clienteRepository.findById(id);
    }

    @Operation(summary = "Busca o serviço pelo ID")
    @GetMapping("/fornecedor/{id}")
    public Optional<FornecedorModel> findbyIdFornecedor(@PathVariable("id") @NotNull Integer id ) throws BadRequestException{
        logConfig.setInfoLogging("(GET) => /api/v1/servicos - Get a service");
        return fornecedorRepository.findById(id);
    }


    @Operation(summary = "Cria um novo cliente")
    @PostMapping("/createcliente")
    public ResponseEntity<ClienteDTO> createCLiente(@RequestBody ClienteDTO clienteDTO) throws BadRequestException{
        logConfig.setInfoLogging("(POST) => /api/v1/servicos - create a new service");
        return thirteenService.createCliente(clienteDTO);
    }

    @Operation(summary = "Cria um novo fornecedor")
    @PostMapping("/createfornecedor")
    public ResponseEntity<FornecedorDTO> createFornecedor(@RequestBody FornecedorDTO fornecedorDTO) throws BadRequestException{
        logConfig.setInfoLogging("(POST) => /api/v1/servicos - create a new service");
        return thirteenService.createFornecedor(fornecedorDTO);
    }


    @Operation(summary = "Deleta o cliente")
    @DeleteMapping("/cliente/delete/{id}")
    public ResponseEntity<ClienteModel> deleteCliente(@PathVariable("id") @NotNull Integer id) throws BadRequestException{
        logConfig.setInfoLogging("(DELETE) => /api/v1/service - delete a service");
        return thirteenService.deleteCliente(id);
    }

    @Operation(summary = "Deleta o fornecedor")
    @DeleteMapping("/fornecedor/delete/{id}")
    public ResponseEntity<FornecedorModel> deleteFornecedor(@PathVariable("id") @NotNull Integer id) throws BadRequestException{
        logConfig.setInfoLogging("(DELETE) => /api/v1/service - delete a service");
        return thirteenService.deleteFornecedor(id);
    }


    @Operation(summary = "Atualiza o cliente")
    @PutMapping("/updatecliente")
    public ResponseEntity<ClienteDTO>  updateCliente(@RequestBody ClienteDTO clienteDTO)  throws BadRequestException {
        logConfig.setInfoLogging("(PUT) => /v1/servicos - Edit a user/servicos");
        return thirteenService.updateClt(clienteDTO);
    }

    @Operation(summary = "Atualiza o cliente")
    @PutMapping("/updatefornecedor")
    public ResponseEntity<FornecedorDTO>  updateFornecedor(@RequestBody FornecedorDTO fornecedorDTO)  throws BadRequestException {
        logConfig.setInfoLogging("(PUT) => /v1/servicos - Edit a user/servicos");
        return thirteenService.updateFornecedor(fornecedorDTO);
    }
}
