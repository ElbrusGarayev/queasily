package app.controller;

import app.entity.User;
import app.security.entity.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping
    ModelAndView handle(Authentication auth){
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userDetails.getUser();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", user);
        return mav;
    }
}
