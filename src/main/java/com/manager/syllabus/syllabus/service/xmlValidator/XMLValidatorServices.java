package com.manager.syllabus.syllabus.service.xmlValidator;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class XMLValidatorServices {

    /**
     * @param xmlString
     * @return True if the string is a valid XML string
     */
    public Boolean isValidXMLString(String xmlString) {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes());
            DefaultHandler saxHandler = new DefaultHandler();
            saxParser.parse(stream, saxHandler);
            return true;
        } catch (ParserConfigurationException e) {
            return false;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @param courseXML
     * @param courseCode
     * @return True if the course xml is a valid xml and matches the course code with existing course code
     */
    public Boolean isValidCourseXMLForUpdating(String courseXML, String courseCode) {
        if (!isValidXMLString(courseXML)) return false;
        String head = "<course courseCode=\"" + courseCode + "\"";
        int lengthCourseXML = courseXML.length();
        int lengthHead = head.length();
        if (lengthCourseXML <= lengthHead) {
            return false;
        }
        if (courseXML.charAt(lengthCourseXML - 2) == '/' &&
                courseXML.charAt(lengthCourseXML - 1) == '>') {
            head += "/>";
            return head.equals(courseXML);
        }
        head += ">";
        lengthHead = head.length();
        if (lengthCourseXML <= lengthHead) {
            return false;
        }
        for (int i = 0; i < lengthHead; i++) {
            if (courseXML.charAt(i) != head.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
