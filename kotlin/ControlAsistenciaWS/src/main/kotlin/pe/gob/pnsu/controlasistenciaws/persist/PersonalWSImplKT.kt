package pe.gob.pnsu.controlasistenciaws.persist

import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto

class PersonalWSImplKT : PersonalWSKT {
    override fun getPersonalWS(
        idpersonal: String,
        iddpendencia: String,
        documentoidentidad: String,
        idtestado: String
    ): List<VwPersonalDto> {

        val list: List<VwPersonalDto> = listOf();
        return list

    }


}