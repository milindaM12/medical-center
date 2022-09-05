package lk.ijse.dep9.clinic.controller;

import lk.ijse.dep9.clinic.security.SecurityContextHolder;

public class ReceiptionistDashboardFormController {
    public void initialize(){
        System.out.println(SecurityContextHolder.getPrinciple());
    }
}
