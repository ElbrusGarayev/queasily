package app.controller;

import app.entity.User;
import app.mail.service.EMailSender;
import app.service.ConfirmationTokenService;
import app.service.UserService;
import app.token.ConfirmationToken;
import app.validation.FormUser;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class RegisterController {

    private final UserService userService;
    private final EMailSender mailSender;
    private final PasswordEncoder encoder;
    private final ConfirmationTokenService confirmationTokenService;

    private static String email;

    @GetMapping("register")
    ModelAndView handle(User user){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("register")
    String handle(@Valid FormUser validUser, Errors errors, User user, Model model){
        if (!errors.hasErrors()){
            if (userService.isExists(user.getEmail())){
                model.addAttribute("msg", "Email is already exists!");
            }
            else{
                System.out.println(user);
                user.setPassword(encoder.encode(user.getPassword()));
                userService.save(user);

                ConfirmationToken confirmationToken = new ConfirmationToken(user);
                confirmationTokenService.save(confirmationToken);

                email = user.getEmail();

                String mailContent = "To confirm your account, please click here : "
                        +"http://localhost:5000/confirm-account?token=" + confirmationToken.getConfirmationToken();
                mailSender.sendMail(email, mailContent);
                return "redirect:/confirm";
            }
        }
        return "register";
    }

    @GetMapping("confirm")
    ModelAndView handleConfirm(){
        ModelAndView mav = new ModelAndView("confirm");
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("confirm-account")
    RedirectView confirmUserAccount(@RequestParam("token") String token){
        Optional<ConfirmationToken> confirmationToken = confirmationTokenService.findToken(token);
        if(confirmationToken.isPresent())
        {
            User user = userService.findByEmail(confirmationToken.get().getUser().getEmail());
            user.setEnabled(true);
            userService.save(user);
            return new RedirectView("/login");
        }
        return new RedirectView("/error");
    }
}
