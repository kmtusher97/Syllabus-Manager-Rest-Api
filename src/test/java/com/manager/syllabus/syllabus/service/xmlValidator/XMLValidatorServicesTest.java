package com.manager.syllabus.syllabus.service.xmlValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorServicesTest {

    private XMLValidatorServices xmlValidatorServices;

    @BeforeEach
    void setUp() {
        xmlValidatorServices = new XMLValidatorServices();
    }

    @Test
    void isValidXMLString1() {
        String xmlString = "<course courseCode=\"CSE 200\"><courseTitle>Viva-Voce</courseTitle><semesterId>2</semesterId><yearId>1</yearId></course>";
        assertEquals(xmlValidatorServices.isValidXMLString(xmlString), true);
    }

    @Test
    void isValidXMLString2() {
        String xmlString = "<course courseCode=\"CSE 200\"><courseTitleViva-Voce</courseTitle><semesterId>2</semesterId><yearId>1</yearId></course>";
        assertEquals(xmlValidatorServices.isValidXMLString(xmlString), false);
    }

    @Test
    void isValidXMLString3() {
        String xmlString = "adsadsad";
        assertEquals(xmlValidatorServices.isValidXMLString(xmlString), false);
    }


    @Test
    void isValidCourseXMLForUpdating1() {
        String courseXML = "<course courseCode=\"CSE 200\"><courseTitle>Viva-Voce</courseTitle><semesterId>2</semesterId><yearId>1</yearId></course>";
        assertEquals(
                xmlValidatorServices.isValidCourseXMLForUpdating(courseXML, "CSE 200"),
                true
        );
    }

    @Test
    void isValidCourseXMLForUpdating2() {
        String courseXML = "<course courseCode=\"CSE 200\"/>";
        assertEquals(
                xmlValidatorServices.isValidCourseXMLForUpdating(courseXML, "CSE 200"),
                true
        );
    }

    @Test
    void isValidCourseXMLForUpdating3() {
        String courseXML = "<course courseCode=\"CSE 205\"><courseTitle>Viva-Voce</courseTitle><semesterId>2</semesterId><yearId>1</yearId></course>";
        assertEquals(
                xmlValidatorServices.isValidCourseXMLForUpdating(courseXML, "CSE 200"),
                false
        );
    }

    @Test
    void isValidCourseXMLForUpdating4() {
        String courseXML = "<c courseCode=\"CSE 205\"><courseTitle>Viva-Voce</courseTitle><semesterId>2</semesterId><yearId>1</yearId></c>";
        assertEquals(
                xmlValidatorServices.isValidCourseXMLForUpdating(courseXML, "CSE 200"),
                false
        );
    }
}