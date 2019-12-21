package com.manager.syllabus.syllabus.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JAXBServicesTest {

    class TestClass {
        public String field1;
        public Integer field2;
    }

    @BeforeEach
    void setUp() {
        TestClass testClass = new TestClass();
        testClass.field1 = "abcd";
        testClass.field2 = 51;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void objectToXmlString() {

    }

    @Test
    void xmlStringToObject() {
    }
}