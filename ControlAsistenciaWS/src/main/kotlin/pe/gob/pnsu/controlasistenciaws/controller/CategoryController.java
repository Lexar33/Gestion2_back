package pe.gob.pnsu.controlasistenciaws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @GetMapping
    public String sayHi(){
        return "hi";
    }

}
