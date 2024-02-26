package net.service.manager.auth.generic.email;

import jakarta.servlet.http.HttpServletRequest;
import net.service.manager.auth.crud.entities.UserAccount;

public interface MailService {
    void sendVerificationToken(HttpServletRequest request, UserAccount user, String token);

    void sendQrCodeLogin(UserAccount user);

    void sendForgotPasswordToken(HttpServletRequest request, UserAccount user, String token);

    void sendResetPassword(UserAccount user);
}
