package edu.miu.cs489.postgresgradle.userprofilemanagement.exception.user;

//unchecked Exception
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
