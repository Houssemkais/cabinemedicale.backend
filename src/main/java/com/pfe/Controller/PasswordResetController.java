package com.pfe.Controller;

import com.pfe.Repository.PasswordResetTokenRepository;
import com.pfe.entities.PasswordResetDto;
import com.pfe.entities.PasswordResetToken;
import com.pfe.entities.User;
import com.pfe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    @Autowired
    private UserService userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);
        if (!resetToken.isPresent()){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.get().isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.get().getToken());
        }

        return "reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        Optional<PasswordResetToken> token = tokenRepository.findByToken(form.getToken());
        if(token.isPresent()){
            User user = token.get().getUser();
//        String updatedPassword = passwordEncoder.encode(form.getPassword());
//        //userService.updatePassword(updatedPassword, user.getId());
            tokenRepository.delete(token.get());
        }


        return "redirect:/login?resetSuccess";
    }

}
