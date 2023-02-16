package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import app.src.entities.RegistrationToken;
import app.src.boundaries.RegisterBoundary;
import app.src.login.ProfessorRegistration;

import persistence.SqliteGatewayImplementation;

@Controller
public class RegistrationController {
    @PostMapping("/save_registration_token")
    public String save_registration_token(RegistrationToken reg) {
        RegistrationToken token = new RegistrationToken(reg.username, reg.password);
        token.id = reg.id;
        RegisterBoundary checker = new ProfessorRegistration(token, new SqliteGatewayImplementation("Accounts"));
        if(checker.register())
            return "redirect:/courses?userid=" + token.id;
        return "redirect:/register";
        /* TODO Add a message dialog that rejects invalid inputs */
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegistrationToken reg = new RegistrationToken("", "");
        model.addAttribute("reg", reg);
        return "routes/register";
    }
}
