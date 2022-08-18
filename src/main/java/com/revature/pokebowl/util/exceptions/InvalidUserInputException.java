package com.revature.pokebowl.util.exceptions;

public class InvalidUserInputException extends RuntimeException{ // unchecked exception
    public InvalidUserInputException(String message) {
        super(message);
    }
}