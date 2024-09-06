package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TPersonal;
import pe.gob.pnsu.controlasistenciaws.repo.IGenericRepo;

import java.time.LocalDate;
import java.util.List;

public interface IControlAsistParcial extends IGenericService<TControlAsistParcial,Integer> {

    List<TControlAsistParcial> listarAsistenciaParcial (LocalDate desde, LocalDate hasta);


}
