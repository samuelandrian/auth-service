package resource.samuelandrian.auth_service.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import resource.samuelandrian.auth_service.entity.CredentialUsers;
import resource.samuelandrian.auth_service.model.CredentialUserRequest;
import resource.samuelandrian.auth_service.repository.CredentialUsersRepository;
import resource.samuelandrian.auth_service.security.BCrypt;


import java.util.Set;

@Service
public class CredentialUsersService {
    @Autowired
    private CredentialUsersRepository credentialUserRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public void register(CredentialUserRequest request) {
        Set<ConstraintViolation<CredentialUserRequest>> constraintViolations = validator.validate(request);
        if(!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if(credentialUserRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username Already Registered");
        }

        CredentialUsers credentialUsers = new CredentialUsers();
        credentialUsers.setUsername(request.getUsername());
        credentialUsers.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));

        credentialUserRepository.save(credentialUsers);
    }
}
