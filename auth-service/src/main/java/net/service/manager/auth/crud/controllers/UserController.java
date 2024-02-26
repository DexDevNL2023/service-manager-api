package net.service.manager.auth.crud.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import net.service.manager.auth.crud.dto.reponse.UserReponse;
import net.service.manager.auth.crud.dto.request.DroitAddRequest;
import net.service.manager.auth.crud.dto.request.LoginRequest;
import net.service.manager.auth.crud.dto.request.UserFormPasswordRequest;
import net.service.manager.auth.crud.dto.request.UserRequest;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.crud.services.UserAccountService;
import net.service.manager.auth.generic.dto.reponse.RessourceResponse;
import net.service.manager.auth.generic.dto.request.SearchRequest;
import net.service.manager.auth.generic.exceptions.InternalException;
import net.service.manager.auth.generic.utils.AppConstants;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('admin') or hasRole('partner') or hasRole('merchant') or hasRole('customer')")
@RefreshScope
@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@Tag(name = "Utilisateurs", description = "API de gestion des utilisateurs")
public class UserController {

	private static final String MODULE_NAME = "UTILISATEURS";

	private final UserAccountService userAccountService;
	private final AuthorizationService authorizationService;

	public UserController(UserAccountService userAccountService, AuthorizationService authorizationService) {
		this.userAccountService = userAccountService;
        this.authorizationService = authorizationService;
    }

	@GetMapping("/search")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<RessourceResponse> search(@NotEmpty @Valid @RequestBody SearchRequest dto) {
		try {
			authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Rechercher un utilisateur", MODULE_NAME + "-SEARCH", "GET", true));
			return new ResponseEntity<>(new RessourceResponse("Recherche de donnée effectuée avec succès!", userAccountService.search(dto.getText())), HttpStatus.OK);
		} catch (InternalException e) {
			return new ResponseEntity<>(new RessourceResponse(false, "Erreur de recherche de donnée.\n\n Cause : " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/me")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<RessourceResponse> getCurrentUser() {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher les details sur l'utilisateur courant", MODULE_NAME+"-GET-CURRENT-USER", "GET", true));
		return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!", userAccountService.loadCurrentUser()), HttpStatus.OK);
	}

	@PostMapping("/logout")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<RessourceResponse> logoutUser(@NotEmpty @Valid @RequestBody LoginRequest loginRequest) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Deconnecter un utilisateur", MODULE_NAME+"-LOGOUT-USER", "POST", false));
        userAccountService.logout(loginRequest);
		return new ResponseEntity<>(new RessourceResponse("Déconnexion de l'utilisateur réussie!"), HttpStatus.OK);
	}

	@PostMapping("/create")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> create(@NotEmpty @Valid @RequestBody UserRequest userRequest, HttpServletRequest request) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Creer un compte utilisateur", MODULE_NAME+"-CREATE-USER-ACCOUNT", "POST", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur créé avec succès!", userAccountService.create(request, userRequest)), HttpStatus.OK);
	}

	@PutMapping("/update")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> update(@NotEmpty @Valid @RequestBody UserRequest userRequest, HttpServletRequest request) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Modifier un compte utilisateur", MODULE_NAME+"-UPDATE-USER-ACCOUNT", "PUT", false));
        return new ResponseEntity<>(new RessourceResponse("L'utilisateur a été mis à jour avec succès!", userAccountService.update(request, userRequest)), HttpStatus.OK);
	}

    @PutMapping("/change/password")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> changePassword(@NotEmpty @Valid @RequestBody UserFormPasswordRequest userFormPasswordRequest) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Modifier le mot de passe d'un compte utilisateur", MODULE_NAME+"-UPDATE-USER-PASSWORD", "PUT", false));
        return new ResponseEntity<>(new RessourceResponse("Mot de passe utilisateur mis à jour avec succès!", userAccountService.changePassword(userFormPasswordRequest)), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> delete(@NotNull @PathVariable("userId") Long userId) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Supprimer un compte utilisateur", MODULE_NAME+"-DELET-USER-ACCOUNT", "DELET", false));
        userAccountService.delete(userId);
		return new ResponseEntity<>(new RessourceResponse("Utilisateur supprimé avec succès!"), HttpStatus.OK);
	}

	@DeleteMapping("/suspend/{userId}")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> suspend(@NotNull @PathVariable("userId") Long userId) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher les permissions d'un utilisateur", MODULE_NAME+"-GET-PERMISSION-USER", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur suspendu avec succès!", userAccountService.suspend(userId)), HttpStatus.OK);
	}

	@GetMapping("/get/{userId}")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> getById(@NotNull @PathVariable("userId") Long userId) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher les details sur un compte utilisateur", MODULE_NAME+"-GET-PERMISSION-USER", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!", userAccountService.getById(userId)), HttpStatus.OK);
	}

	@GetMapping("/get/all")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> getAll() {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher la liste des comptes utilisateurs", MODULE_NAME+"-GET-ALL-USER", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!!", userAccountService.getAll()), HttpStatus.OK);
	}

	@GetMapping("/get/all/by/role/{roleName}")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> getAllByRole(@NotNull @PathVariable("roleName") RoleName roleName) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher la liste des comptes utilisateurs", MODULE_NAME+"-GET-ALL-USER", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!!", userAccountService.getAllByRole(roleName)), HttpStatus.OK);
	}

	@GetMapping("/get/page")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> getAllByPage(@RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
															   @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher la liste des comptes utilisateurs", MODULE_NAME+"-GET-ALL-USER", "GET", false));
        return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!", userAccountService.getAllByPage(page, size)), HttpStatus.OK);
	}

	@GetMapping("/search/{motCle}")
	@SecurityRequirement(name = "Authorization")
    public ResponseEntity<RessourceResponse> search(@NotNull @PathVariable("motCle") String motCle) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Rechercher un utilisateur", MODULE_NAME+"-SEARCH-USER-ACCOUNT", "GET", false));
		return new ResponseEntity<>(new RessourceResponse("Utilisateur trouvé avec succès!", userAccountService.search(motCle)), HttpStatus.OK);
	}

	@PutMapping("/user-autorisations/{userId}")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<RessourceResponse> getAllAutorisations(@NotNull @PathVariable("userId") Long userId) {
		authorizationService.checkIfHasDroit(new DroitAddRequest(MODULE_NAME, "Afficher les permissions d'un utilisateur", MODULE_NAME+"-GET-PERMISSION-USER", "GET", false));
		return new ResponseEntity<>(new RessourceResponse("Permissions trouvées avec succès!", userAccountService.getAutorisations(userId)), HttpStatus.OK);
	}

	// Rest Client Controllers
	@GetMapping("/find/{userId}")
	@SecurityRequirement(name = "Authorization")
    public UserReponse find(@NotNull @PathVariable("userId") Long userId) {
        return userAccountService.getById(userId);
	}

	@GetMapping("/find/all")
	@SecurityRequirement(name = "Authorization")
    public List<UserReponse> findAll() {
        return userAccountService.getAll();
	}
}