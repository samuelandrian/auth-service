package resource.samuelandrian.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import resource.samuelandrian.auth_service.model.CredentialUserRequest;
import resource.samuelandrian.auth_service.model.WebResponse;
import resource.samuelandrian.auth_service.service.CredentialUsersService;

@RestController
public class CredentialUserController {
    @Autowired
    CredentialUsersService credentialUsersService;

    @PostMapping(
            path = "/credential/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody CredentialUserRequest request) {
        credentialUsersService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }
}
