package pe.gob.pnsu.controlasistenciaws.persist

import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto

interface PersonalWSKT {
    fun getPersonalWS(idpersonal : String, iddpendencia:String,documentoidentidad:String,idtestado:String):List<VwPersonalDto>

}