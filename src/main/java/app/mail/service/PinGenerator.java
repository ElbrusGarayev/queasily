package app.mail.service;

import org.springframework.stereotype.Service;

@Service
public class PinGenerator {
    public int generate(){
        return (int) ((Math.random() * (9999 - 1000)) + 1000);
    }
}
