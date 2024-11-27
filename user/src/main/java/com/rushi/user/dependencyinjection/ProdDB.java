package com.rushi.user.dependencyinjection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "env.deploy",havingValue = "production")
public class ProdDB implements DB {


    public String getData(){
        return "Prod Data";
    }


}
