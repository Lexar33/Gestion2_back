package pnsu.gob.pe.sbprocesarhoras.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.repo.ICompHorasRepo;
import pnsu.gob.pe.sbprocesarhoras.service.ICompHorasService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class horasController {

    private final ICompHorasService service;

    @GetMapping
    public List<TCompensacionHoras> readAll(){
        return service.listCompHoras();

    }

    @GetMapping("/hola")
    String procesahoras(){
        return "procesa horas";
    }


}
