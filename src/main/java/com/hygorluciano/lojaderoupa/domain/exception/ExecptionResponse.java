package com.hygorluciano.lojaderoupa.domain.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ExecptionResponse {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public List<ErrorHangle> handle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorlist = Collections.singletonList(exception.getBindingResult().getFieldError());
        List<ErrorHangle> list = new ArrayList<>();
        fieldErrorlist.forEach(erro -> {
            list.add(new ErrorHangle(erro.getDefaultMessage()));
        });
        return list;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class, ValorNaoEncontrado.class})
    public List<ErrorHangle> ErroNotFound(ValorNaoEncontrado exception) {
        List<ErrorHangle> list = new ArrayList<>();
        list.add(new ErrorHangle(exception.getMessage()));
        return list;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ValorExisterExecption.class})
    public List<ErrorHangle> valorExixter(ValorExisterExecption existerExecption) {
        List<ErrorHangle> list = new ArrayList<>();
        list.add(new ErrorHangle(existerExecption.getMessage()));
        return list;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = LoginExecption.class)
    public List<ErrorHangle> loginNotFound(LoginExecption loginExecption){
        List<ErrorHangle> listResponseErro = new ArrayList<>();
        listResponseErro.add(new ErrorHangle(loginExecption.getMessage()));
        return listResponseErro;
    }

}
