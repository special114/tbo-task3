package com.example.thymeleaf.entity;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AddressTest {

    @Test
    public void should_not_throw_on_correct_zip_code() {
        List<String> inputs = List.of("00-001",
                                      "39-300");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().zipCode(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_zip_code() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "12345",
                                            "123456",
                                            "123-45",
                                            "------",
                                            "1-2345",
                                            "1234-5",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "12 345",
                                            "12- 345",
                                            "1",
                                            "<#-@&*");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().zipCode(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_zip_code_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().zipCode(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_zip_code_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().zipCode(input)
                                                                                             .build()));
    }

    @Test
    public void should_not_throw_on_correct_street() {
        List<String> inputs = List.of("St. Gregory",
                                      "Test st",
                                      "Test",
                                      "XxxXx",
                                      "Very strange street name");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().street(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_street() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "asdf@bbb.cc",
                                            "------",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "Street!",
                                            "<#-@&*");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().street(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_street_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().street(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_street_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                   () -> correctAddressBuilder().street(input)
                                                                                                .build()));
    }

    @Test
    public void should_throw_on_incorrect_street_boundary() {
        // zakładając 100 znaków
        List<String> inputs = Arrays.asList("x".repeat(101));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().street(input)
                                                                                     .build()));
    }

    @Test
    public void should_not_throw_on_correct_number() {
        List<String> inputs = List.of("1",
                                      "1000",
                                      "55/21",
                                      "55/21/33",
                                      "57a",
                                      "35a/10");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().street(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_number() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "asdf@bbb.cc",
                                            "------",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "Street!",
                                            "<#-@&*",
                                            "//////");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().number(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_number_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().number(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_number_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                   () -> correctAddressBuilder().number(input)
                                                                                                .build()));
    }

    @Test
    public void should_throw_on_incorrect_number_boundary() {
        // zakładając 20 znaków
        List<String> inputs = Arrays.asList("1".repeat(21));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().number(input)
                                                                                     .build()));
    }

    @Test
    public void should_not_throw_on_correct_complement() {
        List<String> inputs = Arrays.asList("",
                                      "test",
                                      null,
                                      "        ",
                                      "\n",
                                      "57a",
                                      "Second floor");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().complement(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_complement_boundary() {
        // zakładając 400 znaków
        List<String> inputs = Arrays.asList("x".repeat(401));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().complement(input)
                                                                                     .build()));
    }

    @Test
    public void should_not_throw_on_correct_city() {
        List<String> inputs = List.of("New York",
                                      "Warszawa",
                                      "Tokyo",
                                      "Saint-Tropez",
                                      "test");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().city(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_city() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "asdf@bbb.cc",
                                            "------",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "City!",
                                            "<#-@&*",
                                            "//////");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().city(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_city_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().city(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_city_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                   () -> correctAddressBuilder().city(input)
                                                                                                .build()));
    }

    @Test
    public void should_throw_on_incorrect_city_boundary() {
        // zakładając 100 znaków
        List<String> inputs = Arrays.asList("1".repeat(101));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().city(input)
                                                                                     .build()));
    }

    @Test
    public void should_not_throw_on_correct_district() {
        List<String> inputs = List.of("Distrcit1",
                                      "This is long district name",
                                      "District-name");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().district(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_district() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "asdf@bbb.cc",
                                            "------",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "District!",
                                            "<#-@&*",
                                            "//////");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().district(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_district_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().district(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_district_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                   () -> correctAddressBuilder().district(input)
                                                                                                .build()));
    }

    @Test
    public void should_throw_on_incorrect_district_boundary() {
        // zakładając 100 znaków
        List<String> inputs = Arrays.asList("1".repeat(101));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().district(input)
                                                                                     .build()));
    }

    @Test
    public void should_not_throw_on_correct_state() {
        List<String> inputs = List.of("State",
                                      "State with space",
                                      "State-state");

        inputs.forEach(input -> Assertions.assertDoesNotThrow(() -> correctAddressBuilder().state(input)
                                                                                           .build()));
    }

    @Test
    public void should_throw_on_incorrect_state() {
        List<String> inputs = Arrays.asList(null,
                                            "",
                                            "asdf@bbb.cc",
                                            "------",
                                            "      ",
                                            "\t",
                                            "\n",
                                            "State!",
                                            "<#-@&*",
                                            "//////");

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().state(input)
                                                                                     .build()));
    }

    @Test
    public void should_throw_on_xss_state_inputs() {
        getXssInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                () -> correctAddressBuilder().state(input)
                                                                                             .build()));
    }

    @Test
    public void should_throw_on_sql_state_inputs() {
        getSqlInjInputs().forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                                   () -> correctAddressBuilder().state(input)
                                                                                                .build()));
    }

    @Test
    public void should_throw_on_incorrect_state_boundary() {
        // zakładając 100 znaków
        List<String> inputs = Arrays.asList("1".repeat(101));

        inputs.forEach(input -> Assertions.assertThrows(RuntimeException.class,
                                                        () -> correctAddressBuilder().state(input)
                                                                                     .build()));
    }

    private List<String> getXssInputs() {
        return List.of("\"-prompt(8)-\"",
                       "'-prompt(8)-'",
                       "<img/src/onerror=prompt(8)>",
                       "<script src=1 href=1 onerror=\"javascript:alert(1);\"></script>",
                       "<script\\x20type=\"text/javascript\"javascript:alert(1);</script>",
                       "'`\"><\\x3Cscript>javascript:alert(1)</script>",
                       "<script type=\"text/javascript\">alert(1);</script>",
                       "\" onfocus=\"alert(1)\"",
                       "\"><script >alert(1)</script>");
    }

    private List<String> getSqlInjInputs() {
        return List.of("-- or # ",
                       "\" OR  1 = 1 -- -",
                       "'''''''''''''UNION SELECT '2",
                       "1' ORDER BY 1--+",
                       "' UNION SELECT sum(columnname ) from tablename --'",
                       ", (select * from (select(sleep10)))a)",
                       "' OR 1 = 1;");
    }

    @Accessors(fluent = true)
    @Setter
    public static class AddressBuilder {

        private String id;
        private String zipCode;
        private String street;
        private String number;
        private String complement;
        private String district;
        private String city;
        private String state;

        public Address build() {
            Address address = new Address();

            address.setId(id);
            address.setZipCode(zipCode);
            address.setStreet(street);
            address.setNumber(number);
            address.setComplement(complement);
            address.setDistrict(district);
            address.setCity(city);
            address.setState(state);

            return address;
        }
    }

    private AddressBuilder correctAddressBuilder() {
        return new AddressBuilder()
                       .id(UUID.randomUUID().toString())
                       .zipCode("12-345")
                       .street("Test")
                       .number("12")
                       .complement("Simple address complement")
                       .district("Test district")
                       .city("City")
                       .state("State");
    }
}
