package com.pfe.services;

import com.pfe.Repository.PasswordResetTokenRepository;
import com.pfe.entities.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public boolean isTokenExpired(PasswordResetToken token) {
        Calendar cal = Calendar.getInstance();
        return token.getExpiryDate().before(cal.getTime());
    }

    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }
}
