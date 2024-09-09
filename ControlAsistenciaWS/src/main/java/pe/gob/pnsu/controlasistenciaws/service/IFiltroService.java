package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;

import java.time.LocalDate;
import java.util.List;

public interface IFiltroService {

    List<MarcacionPersonalDto> filtrarListaMarcacion(List<MarcacionPersonalDto> listMarcacionPersonal, LocalDate diaAnalizado, String documentoidentidad);


}
