package net.service.manager.auth.crud.services;

import jakarta.transaction.Transactional;
import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.DroitAddRequest;
import net.service.manager.auth.crud.dto.request.DroitFormRequest;
import net.service.manager.auth.crud.dto.request.PermissionFormRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.crud.entities.*;
import net.service.manager.auth.crud.repositories.*;
import net.service.manager.auth.generic.exceptions.RessourceNotFoundException;
import net.service.manager.auth.generic.logging.LogExecution;
import net.service.manager.auth.generic.utils.GenericMapperUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorizationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DroitRepository droitRepository;
    private final ModuleRepository moduleRepository;
    private final PermissionRepository permissionRepository;

    public AuthorizationService(UserRepository userRepository, RoleRepository roleRepository, DroitRepository droitRepository, ModuleRepository moduleRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.droitRepository = droitRepository;
        this.moduleRepository = moduleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    @LogExecution
    public PermissionReponse changeAutorisation(PermissionFormRequest dto) {
        Permission permission = permissionRepository.findById(dto.getPermissionId()).orElseThrow(
                () -> new RessourceNotFoundException("La permission avec l'id " + dto.getPermissionId() + " n'existe pas!")
        );
        permission.setHasPermission(dto.getHasPermission());
        permission = permissionRepository.save(permission);
        // Mapper Dto
        return GenericMapperUtils.map(permission, PermissionReponse.class);
    }

    @Transactional
    @LogExecution
    public DroitReponse changeIsDefaultDroit(DroitFormRequest dto) {
        Droit droit = droitRepository.findById(dto.getDroitId()).orElseThrow(
                () -> new RessourceNotFoundException("Le droit avec l'id " + dto.getDroitId() + " n'existe pas!")
        );
        droit.setIsDefault(dto.getIsDefault());
        droit = droitRepository.save(droit);
        if (droit.getIsDefault()) {
            List<Permission> permissions = permissionRepository.findAllByDroit(droit);
            permissions.forEach(permission -> permission.setHasPermission(true));
            permissionRepository.saveAll(permissions);
        }
        // Mapper Dto
        return GenericMapperUtils.map(droit, DroitReponse.class);
    }

    @Transactional
    @LogExecution
    public void checkIfHasDroit(DroitAddRequest dto) {
        Module module = moduleRepository.findByName(dto.getModule()).orElse(null);
        if (module == null) {
            module = moduleRepository.save(new Module(dto.getModule(), ""));
        }
        Optional<Droit> exist = droitRepository.findByKey(dto.getKey());
        if (exist.isEmpty()) {
            Droit droit = new Droit(dto.getKey(), dto.getLibelle(), dto.getVerbe(), "", dto.getIsDefault(), module);
            droit = droitRepository.save(droit);
            for (Role role : roleRepository.findAll()) {
                Permission permission = new Permission(role, droit, role.getIsSuper() || dto.getIsDefault());
                permissionRepository.save(permission);
            }
        }
        if (!isAuthorized(dto.getKey()))
            throw new RessourceNotFoundException("Vous n'etes pas autoriser a " + dto.getLibelle());
    }

    @Transactional
    @LogExecution
    public List<PermissionReponse> getAutorisations(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new RessourceNotFoundException("Le role avec l'id " + roleId + " n'existe pas!")
        );
        List<Permission> permissions = permissionRepository.findAllByRole(role);
        // Mapper Dto
        return GenericMapperUtils.mapAll(permissions, PermissionReponse.class);
    }

    @Transactional
    @LogExecution
    public UserAccount getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new RessourceNotFoundException("Impossible de retouver l'utilisateur courant!");
        }
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmailOrPhone(userPrincipal.getUsername())
                .orElseThrow(() -> new RessourceNotFoundException("Aucun utilisateur n'existe avec le nom utilisateur " + userPrincipal.getUsername()));
    }

    @Transactional
    @LogExecution
    public boolean isAuthorized(String actionKey) {
        for (Role role : getCurrentUser().getRoles()) {
            if (role.getIsSuper()) {
                return true;
            } else {
                List<Permission> permissions = permissionRepository.findAllByRole(role);
                for (Permission permission : permissions) {
                    if (permission.getHasPermission() && permission.getDroit().getKey().equals(actionKey)) return true;
                }
            }
        }
        return false;
    }

    @Transactional
    @LogExecution
    public Boolean isAdmin() {
        for (Role role : getCurrentUser().getRoles()) {
            if (role.getIsSuper()) {
                return true;
            }
        }
        return false;
    }
}
