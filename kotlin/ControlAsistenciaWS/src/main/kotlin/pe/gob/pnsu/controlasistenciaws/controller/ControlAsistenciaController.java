package pe.gob.pnsu.controlasistenciaws.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ControlAsistenciaController {
    @GetMapping
    public String helloWolrd(){
        return "API CONTROLASISTENCIA";
    }


}
