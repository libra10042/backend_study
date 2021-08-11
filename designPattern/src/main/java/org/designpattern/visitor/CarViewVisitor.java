package org.designpattern.visitor;

public class CarViewVisitor implements ViewVisitor {

    private static final String BUS_STATUS = "현재 버시의 기름 상태";
    private static final String TRUCK_STATUS = "현재 트런의 기름 상태";


    public String visit(final Bus bus) {
        return BUS_STATUS + bus.getFuel();
    }

    public String visit(final Truck truck) {
        return BUS_STATUS + truck.getFuel();
    }
}
