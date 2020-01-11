package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.model.bundle.CourseInputForm;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInputFormServices {

    @Autowired
    private BaseXServices baseXServices;

    private JAXBServices jaxbServices = new JAXBServices();

    public void createInitialForm(String syllabusName, String courseTypeName) {
        CourseInputForm courseInputForm = new CourseInputForm();

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(courseInputForm, false)
                        + " into //syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]"
        );
    }
}
