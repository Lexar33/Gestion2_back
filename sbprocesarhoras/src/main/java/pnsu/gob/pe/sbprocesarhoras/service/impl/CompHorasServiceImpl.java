package pnsu.gob.pe.sbprocesarhoras.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.repo.ICompHorasRepo;
import pnsu.gob.pe.sbprocesarhoras.repo.IGenericRepo;
import pnsu.gob.pe.sbprocesarhoras.service.ICompHorasService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompHorasServiceImpl extends GenericServiceImpl<TCompensacionHoras,Integer> implements ICompHorasService {

    private final ICompHorasRepo repo;


    @Override
    protected IGenericRepo<TCompensacionHoras, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<TCompensacionHoras> listCompHoras() {
        return repo.listAll();
    }



}
