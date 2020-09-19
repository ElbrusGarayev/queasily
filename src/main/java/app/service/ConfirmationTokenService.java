package app.service;

import app.repository.ConfirmationTokenRepository;
import app.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public Optional<ConfirmationToken> findToken(String token){
        return confirmationTokenRepository.findByConfirmationToken(token);
    }

    public void save(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
}
