package com.itsm.userservicemanagment.Exception;

import com.itsm.userservicemanagment.dto.outgoing.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessage> handleException(){
        return new ResponseEntity<>(new ErrorMessage("Unknown error", LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundUserExcption.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundUserException(){
        return new ResponseEntity<>(new ErrorMessage("User not found!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataBaseException.class)
    protected ResponseEntity<ErrorMessage> handleDataBaseException(){
        return new ResponseEntity<>(new ErrorMessage("Problem connection data base!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundGroupException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundGroupException(){
        return new ResponseEntity<>(new ErrorMessage("Group not found!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundRoleException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundRoleException(){
        return new ResponseEntity<>(new ErrorMessage("Role not found!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundUserInGroupException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundUserInGroupException(){
        return new ResponseEntity<>(new ErrorMessage("Not found user in group!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundCategoryException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundCategoryException(){
        return new ResponseEntity<>(new ErrorMessage("Not found Category!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundSubCategoryException.class)
    protected ResponseEntity<ErrorMessage> handleNotFoundSubCategoryException(){
        return new ResponseEntity<>(new ErrorMessage("Not found sub Category!", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<ErrorMessage> handleGroupIsNotApprovalException(){
        return new ResponseEntity<>(new ErrorMessage("Group not for approval", LocalDateTime.now()), HttpStatus.FOUND);
    }

}
