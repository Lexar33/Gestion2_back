package pe.gob.pnsu.controlasistenciaws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionPersonalDto {

    private int idpersonal;
    private String descdependencia;
    private String nombres;
    private String documentoidentidad;
    private Long idmarcacionpersonal;
    private String dia;
    private String sfecha;
    private Date horarioingreso;
    private Date horariosalida;
    private Date fecha;
    private Date ingreso;
    private String sedeingreso;
    private Date salida;
    private String sedesalida;
    private Integer minutotardanza;
    private Integer minutoextrasalida;
    private Integer minutofaltasalida;
    private Integer horasacompensar;
    private Integer horascompensadas;
    private Long jornada;
    private String detallejornada;
    private String detallecompleto;
    private boolean diasustentada;
    private boolean diasolicitada;
    private boolean esferiado;
    private String singreso;
    private String ssalida;

    public MarcacionPersonalDto(int idpersonal,
                                String descdependencia,
                                String nombres,
                                String documentoidentidad,
                                Long idmarcacionpersonal,
                                String dia,
                                String sfecha,
                                Date fecha,
                                Date ingreso,
                                String sedeingreso,
                                Date salida,
                                String sedesalida,
                                Integer minutotardanza,
                                Integer minutoextrasalida,
                                Integer minutofaltasalida,
                                Long jornada,
                                Date horarioingreso,
                                Date horariosalida,
                                String singreso,
                                String ssalida



        ){
        this.idpersonal = idpersonal;
        this.descdependencia = descdependencia;
        this.nombres = nombres;
        this.documentoidentidad = documentoidentidad;
        this.idmarcacionpersonal = idmarcacionpersonal;
        this.dia = dia;
        this.sfecha = sfecha;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.sedeingreso = sedeingreso;
        this.salida = salida;
        this.sedesalida = sedesalida;
        this.minutotardanza = minutotardanza;
        this.minutoextrasalida = minutoextrasalida;
        this.minutofaltasalida = minutofaltasalida;
        this.jornada = jornada;
        this.horarioingreso = horarioingreso;
        this.horariosalida = horariosalida;
        this.singreso = singreso;
        this.ssalida = ssalida;


    }


}
