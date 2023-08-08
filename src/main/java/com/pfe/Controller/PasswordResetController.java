package com.pfe.Controller;

import com.pfe.entities.PasswordResetDto;
import com.pfe.entities.PasswordResetToken;
import com.pfe.entities.User;
import com.pfe.exception.DomainException;
import com.pfe.services.PasswordResetTokenService;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reset-password")
public class PasswordResetController {


//    @Autowired
//    private PasswordResetTokenService tokenService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void resetPassword(@Valid @RequestBody PasswordResetDto resetDto) throws DomainException {
//        String tokenValue = resetDto.getToken(); // Récupérez le token de la requête
//
//        // Vous pouvez ensuite utiliser le token pour récupérer le PasswordResetToken
//        PasswordResetToken resetToken = tokenService.findByToken(tokenValue);
//
//        if (resetToken == null) {
//            throw new DomainException(com.pfe.exception.Error.INVALID_RESET_TOKEN);
//        }
//
//        if (tokenService.isTokenExpired(resetToken)) {
//            throw new DomainException(com.pfe.exception.Error.EXPIRED_RESET_TOKEN);
//        }
//
//        User user = resetToken.getUser();
//        user.setPassword(resetDto.getPassword());
//        userService.updatePassword(user);
//
//        // Optionally: Invalidate the token after successful password reset
//        tokenService.deleteToken(resetToken);
//    }
}
