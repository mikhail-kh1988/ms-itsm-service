package com.itsm.userservicemanagment.Exception;

public class NotFoundUserInGroupException extends RuntimeException {
    public NotFoundUserInGroupException(String message) {
        super(message);
    }
}
