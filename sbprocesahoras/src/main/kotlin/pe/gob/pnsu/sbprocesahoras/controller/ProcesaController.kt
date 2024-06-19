package pe.gob.pnsu.sbprocesahoras.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pe.gob.pnsu.sbprocesahoras.dto.CategoryDto

@RestController
@RequestMapping("/api")
class ProcesaController{

    val cat1= CategoryDto("jose","pedro",11)
    val cat2= CategoryDto("enrique","liza",21)
    val cat3= CategoryDto("yoel","oscanoa",33)

    @GetMapping("/procesa")
    fun blog(): String{
        return "blog"
    }
    @GetMapping("/procesa2")
    fun blog2(): List<CategoryDto>{
        val xx= listOf(cat1,cat2,cat3)
        return xx
    }

}