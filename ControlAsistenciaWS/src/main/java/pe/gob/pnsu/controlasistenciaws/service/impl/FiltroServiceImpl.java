package pe.gob.pnsu.controlasistenciaws.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.service.IFiltroService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FiltroServiceImpl implements IFiltroService {

    @Override
    public List<MarcacionPersonalDto> filtrarListaMarcacion(List<MarcacionPersonalDto> listMarcacionPersonal, LocalDate diaAnalizado, String documentoidentidad) {
        //List<MarcacionPersonalDto> listMarcacion = new List<MarcacionPersonalDto>();
        List<MarcacionPersonalDto> listMarcacionPersonalFiltrado = listMarcacionPersonal.stream()
                .filter((MarcacionPersonalDto t) -> t.getFecha().isEqual(diaAnalizado) && t.getDocumentoidentidad().equals(documentoidentidad))
                .collect(Collectors.toList());


        log.info("Tamaño de lista personal filtrado:"+String.valueOf(listMarcacionPersonalFiltrado.size()));

        List<MarcacionPersonalDto> lista = new ArrayList<MarcacionPersonalDto>(listMarcacionPersonal);
        lista.removeAll(listMarcacionPersonalFiltrado);

        log.info("Tamaño de lista final:"+String.valueOf(lista.size()));
        //listMarcacionPersonal.forEach(t->log.info(t.getSfecha().toString()+" -- "+t.getDocumentoidentidad()+"##"+diaAnalizado.toString()+"##"+documentoidentidad));

        return lista;
        //return null;
        //return listMarcacion;
        //return listMarcacionPersonalFiltrado;
    }
}
