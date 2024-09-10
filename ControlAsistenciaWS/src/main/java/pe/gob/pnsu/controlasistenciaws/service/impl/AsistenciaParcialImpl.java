package pe.gob.pnsu.controlasistenciaws.service.impl;

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
import pe.gob.pnsu.controlasistenciaws.service.IControlAsistService;
import pe.gob.pnsu.controlasistenciaws.service.IFiltroService;
import pe.gob.pnsu.controlasistenciaws.service.IPersonalService;
import pe.gob.pnsu.controlasistenciaws.util.RestResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsistenciaParcialImpl implements IAsistenciaParcial {


    private final IPersonalService oPersonalService;

    private final IControlAsistService oAsistenciaService;

    private final IFiltroService oFiltroService;


/*
    @Autowired
    PersonalWs oPersonalWS;
*/
    @Override
    public RestResponse registrarcontrolasistenciaparcial(String desde, String hasta)
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

        //PERSONAL DE VACACIONES
        List<VacacionDto> listVacaciones = oPersonalService.listarVacacionPersonal(lddesde,ldhasta);

        IntStream.iterate(0,i->i+1)
                .limit(numOfDaysBetween)
                .mapToObj((IntFunction<?>) i ->{

                    listPersonal.forEach( (VwPersonalDto temp) -> {


                        //Date.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant();
                        LocalDate diaAnalizado = LocalDate.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()));
                        String documentoIdentidad= temp.getDocumentoidentidad();
                        Integer idpersonal= temp.getIdpersonal();

                        ////////////////////////////////////////////////////////
                        LocalDate fechaInicio = LocalDate.parse(temp.getFechainicio(),formatter);

                        LocalDate fechaCese = temp.getDfechacesecontrato();
                        Boolean cesePresent= Optional.ofNullable(fechaCese).isPresent();

                        LocalDate fechaBajaAdenda= temp.getDfechabajaadenda();
                        Boolean bajaPresent= Optional.ofNullable(fechaBajaAdenda).isPresent();

                        LocalDate fechaFinAdenda= temp.getDfechafinadenda();
                        Boolean finAdendaPresent = Optional.ofNullable(fechaFinAdenda).isPresent();

                        LocalDate fechaFinContrato= temp.getDfechafincontrato();
                        Boolean finContratoPresent= Optional.ofNullable(fechaFinContrato).isPresent();
                        ////////////////////////////////////////////////////////
                        try{


                            //log.info(diaAnalizado.toString() + "-------------"+fechaInicio.toString());
                            if ( fechaInicio.isBefore(diaAnalizado)
                            ? (cesePresent
                                    ?fechaCese.isAfter(diaAnalizado) || fechaCese.isEqual(diaAnalizado)
                                    :(bajaPresent)
                                    ? fechaBajaAdenda.isAfter(diaAnalizado)|| fechaBajaAdenda.isEqual(diaAnalizado)
                                    :(finAdendaPresent)
                                    ? fechaFinAdenda.isAfter(diaAnalizado)|| fechaFinAdenda.isEqual(diaAnalizado)
                                    :(finContratoPresent)
                                    ?fechaFinContrato.isAfter((diaAnalizado)) || fechaFinContrato.isEqual(diaAnalizado)
                                    :true )
                                    : fechaInicio.isEqual(diaAnalizado)){




                            List<MarcacionPersonalDto> listMarcacionPersonalFiltrado = oFiltroService.filtrarListaMarcacion(listMarcacionPersonal,diaAnalizado,documentoIdentidad);

                            List<TControlAsistParcial> listControlAsistenciaParcialFiltrado = oFiltroService.filtrarControlAsistenciaParcial(listControlAsistenciaParcial,diaAnalizado,idpersonal);



                            }



                        }catch ( Exception e)



                        {
                            log.info(e.toString());
                        }






                    });


                   return null;
                }).collect(Collectors.toList());





        RestResponse holaq = new RestResponse("hola", "prueba", "object");

        return holaq;




    }

}
