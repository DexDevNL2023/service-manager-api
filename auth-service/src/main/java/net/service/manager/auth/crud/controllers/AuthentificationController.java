package net.service.manager.auth.crud.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import net.service.manager.auth.crud.dto.reponse.LoginReponse;
import net.service.manager.auth.crud.dto.request.LoginRequest;
import net.service.manager.auth.crud.dto.request.SignupRequest;
import net.service.manager.auth.crud.services.UserAccountService;
import net.service.manager.auth.generic.dto.reponse.RessourceResponse;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@Tag(name = "Authentifications", description = "API de gestion des authentifications")
public class AuthentificationController {

    private final UserAccountService userAccountService;

    public AuthentificationController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/login")
    public ResponseEntity<RessourceResponse> loginUser(@NotEmpty @Valid @RequestBody LoginRequest loginRequest) {
        LoginReponse loginDto = userAccountService.login(loginRequest);
        return new ResponseEntity<>(new RessourceResponse("L'utilisateur s'est connecté avec succès!", loginDto), HttpStatus.OK);
    }

    @PostMapping("/usingqr/{emailOrPhone}")
    public ResponseEntity<RessourceResponse> verifyCode(@NotEmpty @Valid @RequestParam(value = "emailOrPhone") String emailOrPhone) {
        LoginReponse loginDto = userAccountService.loginUsingQrCode(emailOrPhone);
        return new ResponseEntity<>(new RessourceResponse("L'utilisateur s'est connecté avec succès!", loginDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RessourceResponse> registerUser(@NotEmpty @Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) {
        userAccountService.register(request, signUpRequest);
        return new ResponseEntity<>(new RessourceResponse("Utilisateur enregistré avec succès!"), HttpStatus.OK);
    }

    @PostMapping("/token/verify/{token}")
    public ResponseEntity<RessourceResponse> confirmRegistration(@NotEmpty @Valid @RequestParam(value = "token") String token) {
        String result = userAccountService.validateVerificationToken(token);
        return new ResponseEntity<>(new RessourceResponse("Vérification de l'enregistrement avec succès!", result), HttpStatus.OK);
    }

    @PostMapping("/token/resend/{token}")
    @ResponseBody
    public ResponseEntity<RessourceResponse> resendRegistrationToken(@NotEmpty @Valid @RequestParam(value = "token") String token, HttpServletRequest request) {
        if (!userAccountService.resendVerificationToken(request, token)) {
            return new ResponseEntity<>(new RessourceResponse("Jeton introuvable!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RessourceResponse("Renvoyer le jeton d'enregistrement avec succès!"), HttpStatus.OK);
    }

    @PostMapping("/password/forgot/{email}")
    public ResponseEntity<RessourceResponse> forgotPassword(@NotEmpty @Valid @RequestParam(value = "emailOrPhone") String emailOrPhone, HttpServletRequest request) {
        userAccountService.forgotPassword(request, emailOrPhone);
        return new ResponseEntity<>(new RessourceResponse("Jeton de mot de passe oublié avec succès!"), HttpStatus.OK);
    }

    @PostMapping("/password/reset/{token}")
    public ResponseEntity<RessourceResponse> resetPassword(@NotEmpty @Valid @RequestParam(value = "token") String token) {
        userAccountService.resetPassword(token);
        return new ResponseEntity<>(new RessourceResponse("Réinitialiser le mot de passe avec succès!"), HttpStatus.OK);
    }
}