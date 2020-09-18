package app.service;

import app.entity.User;
import app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;

    public void save(User user){
        userRepo.save(user);
    }

    public boolean isExists(String email){
        return userRepo.findByEmail(email).isPresent();
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email).get();
    }
}
