package pe.gob.pnsu.controlasistenciaws.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.repo.IControlAsistenciaRepo;
import pe.gob.pnsu.controlasistenciaws.repo.IGenericRepo;
import pe.gob.pnsu.controlasistenciaws.service.IControlAsistParcial;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ControlAsisParcialImpl extends CRUDImpl<TControlAsistParcial,Integer> implements IControlAsistParcial {

    private final IControlAsistenciaRepo repo;

    @Override
    protected IGenericRepo<TControlAsistParcial, Integer> getRepo() {
        return repo;
    }


    @Override
    public List<TControlAsistParcial> listarAsistenciaParcial (LocalDate lddesde, LocalDate ldhasta) {

        List<TControlAsistParcial> listControl =repo.listarAsistenciaParcial(lddesde, ldhasta);


        log.info("Control de asistencia parcial {}", listControl.size());

        log.info("===================================================");

        return listControl;
    }

}