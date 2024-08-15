module CoutureRental.ui {

    exports ui;

    requires CoutureRental.core;
    requires CoutureRental.persistence;
    requires CoutureRental.rest;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires spring.web;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    opens ui to javafx.graphics, javafx.fxml;
}

