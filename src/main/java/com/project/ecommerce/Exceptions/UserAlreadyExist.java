package com.project.ecommerce.Exceptions;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(String message)
    {
        super(message);
    }

}
