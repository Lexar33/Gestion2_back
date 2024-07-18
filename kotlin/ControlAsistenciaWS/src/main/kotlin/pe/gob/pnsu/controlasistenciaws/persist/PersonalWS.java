package pe.gob.pnsu.controlasistenciaws.persist;

import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;

import java.util.List;

public interface PersonalWS {

    public List<VwPersonalDto> getPersonalWS (String idpersonal, String iddependencia, String documentoidentidad, String idtestado);

}
