module CoutureRental.core {
    exports core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens core to com.fasterxml.jackson.databind;

}
