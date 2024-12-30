package com.prod.springboot_production_ready.excpetions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){

        super(msg);

    }


}
