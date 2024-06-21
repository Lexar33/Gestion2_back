package pnsu.gob.pe.sbprocesarhoras.service;

import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;

import java.util.List;

public interface ICompHorasService extends IGenericService<TCompensacionHoras,Integer> {

    List<TCompensacionHoras> listCompHoras();
}
