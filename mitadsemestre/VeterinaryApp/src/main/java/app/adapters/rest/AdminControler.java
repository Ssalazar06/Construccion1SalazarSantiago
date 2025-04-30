package app.adapters.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminControler {

    @GetMapping("/")
    public String EstaVivo () {
    return ("Estoy vivo");
    }
}
