package com.iteram.controlador;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iteram.servicios.InterfaceServiceCalculadora;

import io.corp.calculator.TracerImpl;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private InterfaceServiceCalculadora servicioCalculadora;

    private TracerImpl tracer = new TracerImpl();


    @GetMapping(value = "/calcula")
    public ResponseEntity<Double> calcula(@RequestParam(name = "primero") BigDecimal primerNumero,
                                            @RequestParam(name = "segundo") BigDecimal segundoNumero,
                                            @RequestParam(name = "operacion") String operacion) {

        double result = this.servicioCalculadora.calcula(primerNumero, segundoNumero, operacion);
        tracer.trace(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
