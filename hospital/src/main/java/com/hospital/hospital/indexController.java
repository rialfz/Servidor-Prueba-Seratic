/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital;

//import com.prueba.util.RestResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author RicardoZ
 */
@Controller
@EnableAutoConfiguration
@ComponentScan()
public class indexController {
    
   /**
     * Carga una pagina de inicio con el mensaje de saludo hola mundo
     * @return  mensaje
     * @throws IOException 
     */
    @RequestMapping("/")
    public String getIndexPage(@RequestParam(name="name", required=false, defaultValue="Mundo") String name, Model model){
        model.addAttribute("name", name);
        //return "webApp/index";  
       return "index";
    }
    
  /*++-  
    @RequestMapping(value ="/save")
    public RestResponse save(){
    
    return new RestResponse(0, "good");
    }

*/
}
