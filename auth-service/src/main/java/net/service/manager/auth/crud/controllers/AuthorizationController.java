package net.service.manager.auth.crud.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import net.service.manager.auth.crud.dto.request.DroitAddRequest;
import net.service.manager.auth.crud.dto.request.DroitFormRequest;
import net.service.manager.auth.crud.dto.request.PermissionFormRequest;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.generic.dto.reponse.RessourceResponse;
import net.service.manager.auth.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/autorisations")
@Tag(name = "Authorisations", description = "API de gestion des authorisations")
public class AuthorizationController {

    private static final String MODULE_NAME = "AUTORISATIONS";
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PutMapping("/role-autorisations/{roleId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> getAllAutorisations(@NotNull @PathVariable("roleId") Long roleId) {
        authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher les permissions d'un role", MODULE_NAME+"-GET-PERMISSION-ROLE", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Permissions trouvées avec succès!", authorizationService.getAutorisations(roleId)), HttpStatus.OK);
    }

    @PutMapping("/change-autorisation")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> changeAutorisation(@NotEmpty @Valid @RequestBody PermissionFormRequest dto) {
        authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Changer une autorisation", MODULE_NAME+"-CHANGE-PERMISSION", "PUT", false));
        return new ResponseEntity<>(new RessourceResponse("Permission changée avec succès!", authorizationService.changeAutorisation(dto)), HttpStatus.OK);
    }

    @PutMapping("/make-is-default")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> changeIsDefaultDroit(@NotEmpty @Valid @RequestBody DroitFormRequest dto) {
        authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Définir un droit par défaut", MODULE_NAME+"-DROIT-IS-DEFAULT", "PUT", false));
        return new ResponseEntity<>(new RessourceResponse("Permission changée avec succès!", authorizationService.changeIsDefaultDroit(dto)), HttpStatus.OK);
    }

    // Rest Client Controllers
    @GetMapping("/current/user/name")
    @SecurityRequirement(name = "Authorization")
    public Optional<String> getCurrentUserLogin() {
        return SecurityUtils.getCurrentUserLogin();
    }

    @GetMapping("/current/user/jwt")
    @SecurityRequirement(name = "Authorization")
    public Optional<String> getCurrentUserJWT() {
        return SecurityUtils.getCurrentUserJWT();
    }

    @GetMapping("/current/user/have/this/authoritie/{authority}")
    @SecurityRequirement(name = "Authorization")
    public Boolean hasCurrentUserThisAuthority(@NotNull @PathVariable("authority") String authority) {
        return SecurityUtils.hasCurrentUserThisAuthority(authority);
    }

    @GetMapping("/current/user/any/Of/authorities/{authorities}")
    @SecurityRequirement(name = "Authorization")
    public Boolean hasCurrentUserAnyOfAuthorities(@NotNull @PathVariable("authorities") String... authorities) {
        return SecurityUtils.hasCurrentUserAnyOfAuthorities();
    }

    @GetMapping("/current/user/none/Of/authorities/{authorities}")
    @SecurityRequirement(name = "Authorization")
    public Boolean hasCurrentUserNoneOfAuthorities(@NotNull @PathVariable("authorities") String... authorities) {
        return SecurityUtils.hasCurrentUserNoneOfAuthorities();
    }

    @GetMapping("/current/user/is/authenticated")
    @SecurityRequirement(name = "Authorization")
    public Boolean isAuthenticated() {
        return SecurityUtils.isAuthenticated();
    }

    @PutMapping("/add")
    @SecurityRequirement(name = "Authorization")
    public void checkIfHasDroit(@NotEmpty @Valid @RequestBody DroitAddRequest dto) {
        authorizationService.checkIfHasDroit(dto);
    }
}
