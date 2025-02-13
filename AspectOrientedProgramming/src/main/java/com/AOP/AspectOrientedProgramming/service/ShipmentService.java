package com.AOP.AspectOrientedProgramming.service;


public interface ShipmentService {
    String orderPackage(Long orderId);

    String trackPackage(Long orderId);


}
