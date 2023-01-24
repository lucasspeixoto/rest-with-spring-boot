package com.api.restwithspringboot.controllers;

import com.api.restwithspringboot.exceptions.UnsupportedMathOperationException;
import com.api.restwithspringboot.utils.Mathematical;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Diferente dos query params, para path params
 * precisamos colocar value =
 * <p>
 * PathVariable é usada para recuperar dados
 * da url
 */

@RestController
public class MathController extends Mathematical {

    //private static final AtomicLong counter = new AtomicLong();

    private static Double mathOperationHandler(
            String numberOne,
            String numberTwo,
            String operation
    ) {


        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        boolean isOperationValid = validOperations.contains(operation);

        if (!isOperationValid) throw new UnsupportedMathOperationException("Please enter a valid operation");

        Double firstNumber = convertToDouble(numberOne);
        Double secondNumber = convertToDouble(numberTwo);

        return switch (operation) {
            case "sum" -> firstNumber + secondNumber;
            case "subtraction" -> firstNumber - secondNumber;
            case "multiply" -> firstNumber * secondNumber;
            case "division" -> firstNumber / secondNumber;
            default -> 0D;
        };
    }

    @RequestMapping(value = "/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mathOperation(
            @PathVariable(value = "operation") String operation,
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo)
            throws Exception {

        return mathOperationHandler(numberOne, numberTwo, operation);
    }


}
