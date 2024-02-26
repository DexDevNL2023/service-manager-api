package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.UserRequest;
import net.service.manager.auth.crud.entities.UserAccount;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;

public interface UserSearchRepository extends GenericSearchRepository<UserRequest, UserAccount> {
}
