package com.manager.syllabus.syllabus.service.jaxb;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class JAXBServices {

    /**
     * up tp <?
     * xml version="1.0" encoding="UTF-8" standalone="yes"
     * ?>
     */
    private final int CUT_INDEX = 55;

    /**
     * @param object
     * @param keepInitialXmlVersionTag
     * @param <Obj>
     * @return String (the xml string of an object)
     */
    public <Obj> String objectToXmlString(Obj object, boolean keepInitialXmlVersionTag) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance((Class<Obj>) object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object, stringWriter);

            if (!keepInitialXmlVersionTag) {
                return (stringWriter.toString()).substring(CUT_INDEX);
            } else {
                return stringWriter.toString();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param xmlObject
     * @param object
     * @param <Obj>
     * @return Object (java object of an xml string)
     */
    public <Obj> Object xmlStringToObject(String xmlObject, Obj object) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance((Class<Obj>) object.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader stringReader = new StringReader(xmlObject);
            return (Obj) unmarshaller.unmarshal(stringReader);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
