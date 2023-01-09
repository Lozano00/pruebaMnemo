package com.iteram.servicios;

import com.iteram.utilidades.Operacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implementación básica de la interfaz {@link InterfaceServiceCalculadora}
 */
@Service
public class ServiceCalculadora implements InterfaceServiceCalculadora {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCalculadora.class);


    @Override
    public double calcula(BigDecimal primerNumero, BigDecimal segundoNumero, String opTexto) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calculando resultado para : {} {} {}", primerNumero, segundoNumero, opTexto);
        }

        Operacion operacion = Operacion.desdeValor(opTexto);

        if(operacion == null) {
            throw new RuntimeException("Operación imposible de procesar: " + opTexto);
        }

        switch (operacion) {
            case SUMA:
                return primerNumero.add(segundoNumero).doubleValue();
            case RESTA:
                return primerNumero.subtract(segundoNumero).doubleValue();
            default:
                if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("Operación no soportada para ser calculada: {}", operacion);
                }
                throw new RuntimeException("Operación no soportada para ser calculada: " + operacion.toString());

        }
    }
}
