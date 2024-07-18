package pe.gob.pnsu.controlasistenciaws.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api2")
class ControlAsistenciaCon {

    private val log= LoggerFactory.getLogger(this.javaClass)

    //@Autowired
    //ControlAsistenciaService oControlAsistenciaService;

    //@Autowired
    //var oControlAsistenciaParcial: ControlAsistenciaParcial? = null
    @GetMapping
    fun helloWolrd():String{
        log.info("infor log")
        return "API CONTROLASISTENCIA2";


    }



}