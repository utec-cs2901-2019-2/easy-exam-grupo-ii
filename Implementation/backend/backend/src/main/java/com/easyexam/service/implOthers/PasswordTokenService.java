package com.easyexam.service.implOthers;

import com.easyexam.model.aux.PasswordResetToken;
import com.easyexam.model.User;
import com.easyexam.repository.IPasswordResetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswordTokenService {

    @Autowired
    private IPasswordResetRepo tokenRepository;

    public PasswordResetToken save(PasswordResetToken item){
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(item.getToken());
        token.setUser(item.getUser());
        token.setExpiryDate(item.getExpiryDate());  
        return tokenRepository.save(token);
    }

    public PasswordResetToken update(PasswordResetToken item){
        return tokenRepository.save(item);
    }

    public void delete(String token){
        tokenRepository.delete(findOne(token));
    }

    public void delete(PasswordResetToken passwordResetToken){
        tokenRepository.delete(passwordResetToken);
    }

    public PasswordResetToken findOne(String token){
        return tokenRepository.findByToken(token);
    }

    public PasswordResetToken findOneByUser(User user){
        return tokenRepository.findByUser(user);
    }
}
