package com.rushi.user.dependencyinjection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "env.deploy",havingValue = "development")
public class DevDB implements DB {



    public String getData(){
        return "Dev Data";
    }


}
