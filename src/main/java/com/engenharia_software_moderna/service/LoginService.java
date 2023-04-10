package com.engenharia_software_moderna.service;

import com.engenharia_software_moderna.entities.dto.LoginDTO;
import com.engenharia_software_moderna.entities.model.LoginModel;
import com.engenharia_software_moderna.repository.LoginRepository;
import com.engenharia_software_moderna.config.LogConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final LogConfig logConfig;

    PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository, LogConfig logConfig) {
        this.loginRepository = loginRepository;
        this.logConfig = logConfig;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public ResponseEntity<Optional<LoginModel>> findById(Integer id){
        try{
            Optional<LoginModel> optionalLoginModel = loginRepository.findById(id);
            if(optionalLoginModel.isEmpty()){
                LogConfig.warn(HttpStatus.NO_CONTENT, "Não foi possivel encontrar o Login perlo Id" + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(optionalLoginModel);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data." + ex);
        }
    }

    public ResponseEntity<LoginDTO> createLogin(LoginDTO loginDTO){
        try{
            String encoders = this.passwordEncoder.encode(loginDTO.getPassword());
            loginDTO.setPassword(encoders);
            loginRepository.save(loginDTO.transformToObject());
            return ResponseEntity.status(HttpStatus.CREATED).body(loginDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data." + ex);
        }
    }

    public ResponseEntity<LoginDTO> updateLogin(LoginDTO loginDTO){
        try{
            Optional<LoginModel> optionalLoginModel = loginRepository.findById(loginDTO.getId());
            if(optionalLoginModel.isPresent()){
                loginRepository.CripyLogin(optionalLoginModel.get().getId(),
                        loginDTO.getEmail(),
                        loginDTO.getPassword());
                String encoders = this.passwordEncoder.encode(loginDTO.getPassword());
                loginDTO.setPassword(encoders);
                return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginDTO);
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data." + ex);
        }
    }

    public ResponseEntity<LoginModel> deletelogin(Integer id){
        try{
            Optional<LoginModel> loginModel = loginRepository.findById(id);
            if(loginModel.isPresent()){
                loginRepository.delete(loginModel.get());
                return ResponseEntity.status(HttpStatus.OK).body(loginModel.get());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginModel());
        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error trying to get data" + ex);
        }
    }


    public ResponseEntity<Boolean> authenticateLogin(LoginDTO loginDTO){
        Optional<LoginModel> optLoginModel = loginRepository.findById(loginDTO.getId());
        if(optLoginModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        LoginModel model = optLoginModel.get();
        boolean valid = passwordEncoder.matches(loginDTO.getPassword(), model.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return  ResponseEntity.status(status).body(valid);
    }

}