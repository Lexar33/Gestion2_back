package pe.gob.pnsu.controlasistenciaws.service.impl;

import jakarta.ejb.Local;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.DocumentoDto;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.dto.VacacionDto;
import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TFeriado;
import pe.gob.pnsu.controlasistenciaws.service.IAsistenciaParcial;
import pe.gob.pnsu.controlasistenciaws.service.IControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.service.IPersonalService;
import pe.gob.pnsu.controlasistenciaws.util.RestResponse;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsistenciaParcialImpl implements IAsistenciaParcial {


    private final IPersonalService oPersonalService;

    private final IControlAsistParcial oAsistenciaService;


/*
    @Autowired
    PersonalWs oPersonalWS;
*/
    @Override
    public RestResponse registrarcontrolasistenciaparcial(String idpersonal, String documentoidentidad, String desde, String hasta)
    {

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //formatter.withLocale():
        LocalDate lddesde = LocalDate.parse(desde,formatter);
        LocalDate ldhasta= LocalDate.parse(hasta,formatter);

        //DIFERENCIA DE DIAS
        long numOfDaysBetween = ChronoUnit.DAYS.between(lddesde, ldhasta) +1;
        log.info("Cantidad de Dias {}", numOfDaysBetween);

        log.info("===================================================");



       //LISTA TODOS LO USUARIOS A LA FECHA
        List<VwPersonalDto> listPersonal= oPersonalService.listarPersonalFechaHasta();

        //LISTA T_CONTROL_ASIST_PARCIAL
        List<TControlAsistParcial> listControlAsistenciaParcial = oAsistenciaService.listarAsistenciaParcial(lddesde,ldhasta);

        //LISTA MARCACION PERSONAL
        List<MarcacionPersonalDto> listMarcacionPersonal = oPersonalService.listarMarcacionPersonal(lddesde,ldhasta);

        //LISTA FERIADOS
        List<TFeriado> listFeriado = oPersonalService.listFeriado(lddesde,ldhasta);

        //LISTAR DOCUMENTOS REGISTRADOS
        List<DocumentoDto> listDocumentos= oPersonalService.listarDocumentoReporte(lddesde,ldhasta);

        List<VacacionDto> listVacaciones = oPersonalService.listarVacacionPersonal(lddesde,ldhasta);






        RestResponse holaq = new RestResponse("hola", "prueba", "object");

        return holaq;




    }

}
