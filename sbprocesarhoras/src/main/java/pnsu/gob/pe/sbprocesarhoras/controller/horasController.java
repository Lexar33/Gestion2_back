package pnsu.gob.pe.sbprocesarhoras.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pnsu.gob.pe.sbprocesarhoras.dto.CompensacionHorasDto;
import pnsu.gob.pe.sbprocesarhoras.service.ICompHorasService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class horasController {

    private final ICompHorasService service;

    @GetMapping
    public String helloWorld(){
        return "API SBPROCESAHORAS";
    }
/*
    @GetMapping("/hola")
    String procesahoras(){
        return "procesa horas";
    }
*/

}
