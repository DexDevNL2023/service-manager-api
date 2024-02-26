package net.service.manager.auth.crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import net.service.manager.auth.crud.entities.token.AbstractToken;

import java.io.Serializable;

@Entity
@Table(name = "tokens")
public class VerifyToken extends AbstractToken implements Serializable {

    public VerifyToken() {
    }

    public VerifyToken(String token) {
        super(token);
    }

    public VerifyToken(UserAccount user, String token) {
        super(user, token);
    }
}
