package com.skypro.employee_book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Внесены не все данные")
public class Exception extends RuntimeException{

}
