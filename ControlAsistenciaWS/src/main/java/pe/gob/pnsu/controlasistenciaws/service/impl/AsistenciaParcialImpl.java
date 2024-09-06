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





        IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj((IntFunction<?>) i -> {

                    log.info ("======================= dia: {} ====================",i);
                    listPersonal.forEach((VwPersonalDto temp) -> {


                        /************************************************************/
                        LocalDate fechainicio= LocalDate.parse(temp.getFechainicio(),formatter);
                        LocalDate dfechacesecontrato= temp.getDfechacesecontrato();
                        LocalDate dfechabajaadenda= temp.getDfechabajaadenda();
                        LocalDate dfechafinadenda = temp.getDfechafinadenda();
                        LocalDate dfechafincontrato = temp.getDfechafincontrato();



                        /************************************************************/


                        LocalDate lddesdeplusidays=LocalDate.from(lddesde.plusDays(i).atStartOfDay());
                        log.info("lddesdeplusidays"+lddesdeplusidays.toString());


                        /************************************************************/


                        if( fechainicio.isBefore(lddesdeplusidays)
                            ? (Optional.ofNullable(dfechacesecontrato).isPresent()
                                ? dfechacesecontrato.isAfter(lddesdeplusidays) || dfechacesecontrato.isEqual(lddesdeplusidays)
                                : (Optional.ofNullable(dfechabajaadenda).isPresent()
                                ? dfechabajaadenda.isAfter(lddesdeplusidays) || dfechabajaadenda.isEqual(lddesdeplusidays)
                                : (Optional.ofNullable(dfechafinadenda).isPresent()
                                ? dfechafinadenda.isAfter(lddesdeplusidays) || dfechafinadenda.isEqual(lddesdeplusidays)
                                : (Optional.ofNullable(dfechafincontrato).isPresent()
                                ? dfechafincontrato.isAfter(lddesdeplusidays) || dfechafincontrato.isEqual(lddesdeplusidays)
                                : true))))
                            :fechainicio.isEqual(lddesdeplusidays)){
                            //log.info("llegue hasta aca"+ fechainicio.toString());
                        }




                        //FILTRA MARCACION DE PERSONAL POR DNI DE LA PERSONA
                        List<MarcacionPersonalDto> listMarcacionPersonalFiltrado = listMarcacionPersonal.stream()
                                .filter((MarcacionPersonalDto t)
                                        -> t.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(lddesdeplusidays) &&
                                        t.getDocumentoidentidad().equals(temp.getDocumentoidentidad())).toList();


                        listMarcacionPersonal.removeAll(listMarcacionPersonalFiltrado);
/*
                        //FILTRA ASISTENCIA PARCIAL POR IDPERSONAL
                        List<TControlAsistParcial> listControlAsistenciaParcialFiltrado = listControlAsistenciaParcial.stream()
                                .filter((TControlAsistParcial t)
                                        -> EAIUtil.formatoFechaOut.format(t.getFecha()).equals(EAIUtil.formatoFechaOut.format(Date.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant()))) && t.getIdpersonal().getIdpersonal().equals(temp.getIdpersonal()))
                                .collect(Collectors.toList());
                        listControlAsistenciaParcial.removeAll(listControlAsistenciaParcialFiltrado);
                        */


                    });
/*
                    Long ix= (long)i;
                    log.info(ix.toString());
                    log.info("hola");
                    */
                    return null;

                }).collect(Collectors.toList());



        RestResponse holaq = new RestResponse("hola", "prueba", "object");

        return holaq;




    }

}
