package app.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalAppExHandler implements ErrorController {

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @GetMapping("/error")
  public String handleError(HttpServletRequest rq, Model model) {
    Object status = rq.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    String code = status.toString();
      if (code.equals("404")) {
        model.addAttribute("code", 404);
        model.addAttribute("message", "This page is unknown or does not exist!");
        return "error";
      }
      if (code.equals("500")) {
        model.addAttribute("code", 500);
        model.addAttribute("message", "Oops, something went wrong!");
        return "error";
      }
      model.addAttribute("code", 400);
      model.addAttribute("message", "Bad request!");
      return "error";
  }

}
