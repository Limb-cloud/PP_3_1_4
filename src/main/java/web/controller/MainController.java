package web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class MainController {

  @GetMapping("login")
  String printLogin() {

    return "login";
  }
}
