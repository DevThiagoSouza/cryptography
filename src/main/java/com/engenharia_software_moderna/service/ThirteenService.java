package com.engenharia_software_moderna.service;

import com.engenharia_software_moderna.config.LogConfig;
import com.engenharia_software_moderna.entities.dto.ClienteDTO;
import com.engenharia_software_moderna.entities.model.FornecedorModel;
import com.engenharia_software_moderna.exceptions.BadRequestException;
import com.engenharia_software_moderna.entities.dto.FornecedorDTO;
import com.engenharia_software_moderna.entities.model.ClienteModel;
import com.engenharia_software_moderna.repository.ClienteRepository;
import com.engenharia_software_moderna.repository.FornecedorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;


@Service
public class ThirteenService {

    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;

    public ThirteenService(ClienteRepository clienteRepository, FornecedorRepository fornecedorRepository) {
        this.clienteRepository = clienteRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public Page<ClienteModel> findAllCliente(Pageable pageable){
        Page<ClienteModel> response = clienteRepository.findAll(pageable);
        if(response.isEmpty()){
            LogConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum serviço");
            throw new BadRequestException("Não foi encontrado nenhum serviço");
        }
        return response;
    }

    public Page<FornecedorModel> findAllFornecedor(Pageable pageable){
        Page<FornecedorModel> response = fornecedorRepository.findAll(pageable);
        if(response.isEmpty()){
            LogConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum serviço");
            throw new BadRequestException("Não foi encontrado nenhum serviço");
        }
        return response;
    }


    public ResponseEntity<Optional<ClienteModel>> findByIdCliente(Integer id){
        try{
            Optional<ClienteModel> clienteModel = clienteRepository.findById(id);
            if(clienteModel.isEmpty()){
                LogConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum serviço no ID informado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(clienteModel);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }

    public ResponseEntity<Optional<FornecedorModel>> findByIdFornecedor(Integer id){
        try{
            Optional<FornecedorModel> forn = fornecedorRepository.findById(id);
            if(forn.isEmpty()){
                LogConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum serviço no ID informado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(forn);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }


    public ResponseEntity<ClienteDTO> createCliente(ClienteDTO clienteDTO){
        try{
            if(isDataValid(clienteDTO)){
                clienteRepository.save(clienteDTO.transformToObject());
                return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }

    public ResponseEntity<FornecedorDTO> createFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(isDataValid(fornecedorDTO)){
                fornecedorRepository.save(fornecedorDTO.transformToObject());
                return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorDTO);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fornecedorDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }


    public ResponseEntity<ClienteModel> deleteCliente(Integer id){
        try{
            Optional<ClienteModel> clienteModel = clienteRepository.findById(id);
            if(clienteModel.isPresent()){
                clienteRepository.delete(clienteModel.get());
                return ResponseEntity.status(HttpStatus.OK).body(clienteModel.get());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ClienteModel());
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }

    public ResponseEntity<FornecedorModel> deleteFornecedor(Integer id){
        try{
            Optional<FornecedorModel> fornecedorModel = fornecedorRepository.findById(id);
            if(fornecedorModel.isPresent()){
                fornecedorRepository.delete(fornecedorModel.get());
                return ResponseEntity.status(HttpStatus.OK).body(fornecedorModel.get());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FornecedorModel());
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }


    public ResponseEntity<ClienteDTO> updateClt(ClienteDTO clienteDTO){
        try{
            if (isDataValid(clienteDTO)) {
                Optional<ClienteModel> response = clienteRepository.findById(clienteDTO.getId());
                if(response.isPresent()){
                    clienteRepository.updateCliente(response.get().getId(),
                        clienteDTO.getNome(),
                    clienteDTO.getSobrenome(),
                    clienteDTO.getCpf(),
                    clienteDTO.getRua(),
                    clienteDTO.getNumero(),
                    clienteDTO.getComplemento(),
                    clienteDTO.getTelefone(),
                    clienteDTO.getCep(),
                    clienteDTO.getCidade(),
                    clienteDTO.getUf());

                    return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error trying to get data" + ex);
        }
    }

    public ResponseEntity<FornecedorDTO> updateFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if (isDataValid(fornecedorDTO)) {
                Optional<ClienteModel> response = clienteRepository.findById(fornecedorDTO.getId());
                if(response.isPresent()){
                    clienteRepository.updateCliente(response.get().getId(),
                            fornecedorDTO.getNome(),
                            fornecedorDTO.getNomeFantasia(),
                            fornecedorDTO.getCnpj(),
                            fornecedorDTO.getRua(),
                            fornecedorDTO.getNumero(),
                            fornecedorDTO.getComplemento(),
                            fornecedorDTO.getTelefone(),
                            fornecedorDTO.getCep(),
                            fornecedorDTO.getCidade(),
                            fornecedorDTO.getUf());

                    return ResponseEntity.status(HttpStatus.OK).body(fornecedorDTO);
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fornecedorDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error trying to get data" + ex);
        }
    }



    public boolean isDataValid(ClienteDTO clienteDTO){
        return clienteDTO.getNome() != null && !Objects.equals(clienteDTO.getNome(), "");
    }
    public boolean isDataValid(FornecedorDTO fornecedorDTO){
        return fornecedorDTO.getNome() != null && !Objects.equals(fornecedorDTO.getNome(), "");
    }

}
