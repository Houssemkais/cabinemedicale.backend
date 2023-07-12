package com.pfe.Controller;

import com.pfe.Repository.PasswordResetTokenRepository;
import com.pfe.entities.Mail;
import com.pfe.entities.PasswordForgotDto;
import com.pfe.entities.PasswordResetToken;
import com.pfe.entities.User;
import com.pfe.exception.DomainException;
import com.pfe.services.EmailService;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/forgot-password")
public class PasswordForgotController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping
    public void processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form) throws DomainException {
        User user = userService.findUserByEmail(form.getEmail());
        if (user == null) {
            throw new DomainException(com.pfe.exception.Error.USER_EMAIL_NOT_FOUND);
        }
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);
        Mail mail = new Mail();
        mail.setFrom("cabinet.medicale.houssem@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getUsername());
        model.put("url", "localhost:8080/reset-password?token=" + token.getId());
        mail.setModel(model);
        try {
            emailService.sendEmail(mail);
        } catch (Exception e) {
            throw new DomainException(com.pfe.exception.Error.SERVER_ERROR);
        }
    }
}
