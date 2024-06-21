package pnsu.gob.pe.sbprocesarhoras.repo;

import org.springframework.data.jpa.repository.Query;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;

import java.util.List;

public interface ICompHorasRepo extends IGenericRepo<TCompensacionHoras,Integer> {

    @Query(value = "SELECT * FROM T_COMPENSACION_HORAS t",nativeQuery = true)
    List<TCompensacionHoras> listAll();


}
