package com.proyecto.proyectoWeb.controllers;

import com.proyecto.proyectoWeb.models.*;
import com.proyecto.proyectoWeb.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ControllerRestaurante {
    @Autowired
    private final ServicesPropietario propietarioServices;

    @Autowired
    private final ServicesRestaurante restauranteServices;

    @Autowired
    private final ServicesComida comidaServices;

    private List<Restaurante> ListaRestaurante = new ArrayList<>();

    public ControllerRestaurante( ServicesPropietario propietarioServices, ServicesRestaurante restauranteServices, ServicesComida comidaServices){

        this.propietarioServices = propietarioServices;
        this.restauranteServices = restauranteServices;
        this.comidaServices = comidaServices;
    }
    @GetMapping("/")
    public String home(Model model) {

        List<Propietario> propietario = propietarioServices.getAllPropietarios();
        model.addAttribute("propietarios", propietario);
        return "propietarios";
    }

    @GetMapping("/propietarios/registro")
    public String formPropietario(Model model){
        model.addAttribute("propietarios", new Propietario());
        model.addAttribute("restaurantes", restauranteServices.findAll());
        return "registroPropietario";
    }
    @PostMapping("/propietarios/registro")
    public Object savePropietario(@ModelAttribute("propietario") Propietario propietario, Model model) {
        String mensaje = new String("null");
        if(!propietario.email.contains(String.valueOf("@"))){
            mensaje = "Email erróneo";
        }
        if(!mensaje.equals("null")){
            model.addAttribute("mensaje", mensaje);
            return "/error";
        }

        propietarioServices.save(propietario);
        return new RedirectView("/");
    }


    @GetMapping("/propietarios/delete/{id}")
    public RedirectView deletePropietario(@PathVariable int id){
        propietarioServices.deletePropietario(id);
        return new RedirectView("/");

    }

    @GetMapping("/propietarios/edit/{id}")
    public String editPropietario(@PathVariable int id, Model model){
        Optional<Propietario> aux = propietarioServices.findById(id);
        Propietario propietario = aux.orElseThrow(() ->
                new RuntimeException("El usuario no existe")
        );
        model.addAttribute("propietario", propietario);
        return ("modificaPropietario");
    }

    @PostMapping("/propietarios/edit/{id}")
    public Object savePropietario(@ModelAttribute("propietario") Propietario propietario, @PathVariable int id, Model model) {
        String mensaje = new String("null");
        if(!propietario.email.contains(String.valueOf("@"))){
            mensaje = "Email erróneo";
        }
        if(!mensaje.equals("null")){
            model.addAttribute("mensaje", mensaje);
            return "/error";
        }
        propietario.setId(id);
        propietarioServices.save(propietario);
        return new RedirectView("/");
    }

    @GetMapping("/propietarios/Restaurante/{id}")
    public String editRestaurantePropietario(@PathVariable int id, Model model){
        Optional<Propietario> aux = propietarioServices.findById(id);
        Propietario propietario = aux.orElseThrow(() ->
                new RuntimeException("El usuario no existe")
        );
        //Aqui del repositirio Restaurante, cojo todos los Restaurante y los paso al modal. Podría pasar al modal solo los que no estén ya en el alumno y le ahorro trabajo a la vista y la lógica de después.
        model.addAttribute("propietario", propietario);
        return ("editar");
    }

    @GetMapping("/welcome/{nombre}")
    public String welcome(Model model, @PathVariable String nombre){
        model.addAttribute("persona", nombre);
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcomeDefault(Model model){
        model.addAttribute("persona", "desconocido");
        return "welcome";
    }


    @GetMapping("/Comidas")
    public String ComidasInicio(Model model){
        model.addAttribute("comidas", comidaServices.findAll());
        return "comidas";
    }

    @GetMapping("/Comidas/nuevo")
    public String ComidasNuevo(Model model){
        model.addAttribute("comidas", new Comida());
        model.addAttribute("restaurantes", restauranteServices.findAll());
        return "registroComida";
    }

    @PostMapping("/Comidas/nuevo")
    public RedirectView guardarComida(@ModelAttribute("Comida") Comida comida, Model model) {
        //Aqui se debería hacer control por si ya existe
        comidaServices.save(comida);
        return new RedirectView("/comidas");

    }

    //A partir de aquí las rutas Restaurante
    @GetMapping("/Restaurante/nuevo")
    public String RestauranteNuevo(Model model){
        model.addAttribute("restaurantes", new Restaurante());
        return "registroRestaurante";
    }

    @PostMapping("/Restaurante/nuevo")
    public RedirectView guardarRestaurante(@ModelAttribute("Restaurante") Restaurante restaurante, Model model) {
        restauranteServices.save(restaurante);
        return new RedirectView("/restaurantes");
    }

    @GetMapping("/Restaurante")
    public String Restaurante(Model model){
        model.addAttribute("restaurantes", restauranteServices.findAll());
        return "restaurantes";
    }


}
