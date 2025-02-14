package com.AOP.AspectOrientedProgramming.service.impl;

import com.AOP.AspectOrientedProgramming.aspects.MyLogging;
import com.AOP.AspectOrientedProgramming.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    @Transactional
    @MyLogging
    public String orderPackage(Long orderId) {

        //log.info("Order Package is called");

        try{
            log.info("Processing the order");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            log.error("Error occurred while processing the order");
        }

        return "Order has been processed successfully ,orderId:"+orderId;
    }

    @Override

    public String trackPackage(Long orderId) {

        log.info("Track Package is called");

        try {
            //log.info("Tracking the order");
            Thread.sleep(500);
            throw new RuntimeException("Exception occurred during trackPackage");
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
