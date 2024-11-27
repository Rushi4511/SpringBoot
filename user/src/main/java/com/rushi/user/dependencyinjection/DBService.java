package com.rushi.user.dependencyinjection;

import org.springframework.stereotype.Service;



@Service
public class DBService {

//    @Autowired
//    DevDB db1;
//
//    @Autowired
//    ProdDB db2;

//    @autowired
//    DB db;
    final private DB db;
    public DBService(DB db){                      //most prefered way of injection
        this.db=db;                                //study about (setter injection)
    }


    public String getData(){
        return db.getData();
    }
}
