package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.dto.ExceptionResponseDto;
import com.avance.test.avancepaytest.exception.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by a.kuci on 7/28/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(value = RestApiException.class)
    public ResponseEntity<ExceptionResponseDto> handleRestApiException(RestApiException restApiException) {

        ExceptionResponseDto responseDto = new ExceptionResponseDto();
        responseDto.setErrorMessage(restApiException.getErrorMessage());
        responseDto.setMessage(restApiException.getMessage());
        responseDto.setTimestamp(LocalDateTime.now().toString());

        ResponseEntity<ExceptionResponseDto> response;

        switch (restApiException.getExceptionCause()) {
            case ERROR_STORING_RESOURCE:
                response = new ResponseEntity<ExceptionResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
                break;
            case NO_RESOURCE_FOUND:
                response = new ResponseEntity<ExceptionResponseDto>(responseDto, HttpStatus.NOT_FOUND);
                break;
            default:
                responseDto.setMessage("Something went wrong in server :(. Please contact administrator");
                response = new ResponseEntity<ExceptionResponseDto>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
