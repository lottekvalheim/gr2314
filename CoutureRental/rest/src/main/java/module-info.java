module CoutureRental.rest {
    exports rest;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires CoutureRental.core;
    requires CoutureRental.persistence;

    opens rest to spring.context, spring.web;

}