package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseTypeServices {

    @Autowired
    private BaseXServices baseXServices;

    private static JAXBServices jaxbServices = new JAXBServices();

    @Autowired
    private CourseInputFormServices courseInputFormServices;

    /**
     * @param syllabusName
     * @return
     */
    private String getCourseTypes(String syllabusName) {
        return baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]//courseTypes"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    private Boolean isCourseTypeExist(String syllabusName, String courseTypeName) {
        return (
                baseXServices.read(
                        "exists(//syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\"" +
                                courseTypeName + "\"])"
                ).equals("true")
        );
    }

    /**
     * @param syllabusName
     * @return
     */
    public List<String> getAllCourseTypeNames(String syllabusName) {
        String result = baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]//courseTypes//courseType/@*/(data())"
        );
        String[] courseTypeNames = result.split("\n");
        return Arrays.asList(courseTypeNames);
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public String getCourseTypeByCourseTypeName(String syllabusName, String courseTypeName) {
        if (isCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }
        return baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\"" +
                        courseTypeName + "\"]"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public String addNewCourseType(String syllabusName, String courseTypeName) {
        if (isCourseTypeExist(syllabusName, courseTypeName) == true) {
            return getCourseTypes(syllabusName);
        }
        baseXServices.write(
                "insert node <courseType name=\"" + courseTypeName + "\"/> into //syllabus[@name=\"" +
                        syllabusName + "\"]/courseTypes"
        );
        courseInputFormServices.createInitialForm(syllabusName, courseTypeName);
        return getCourseTypes(syllabusName);
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public String deleteCourseType(String syllabusName, String courseTypeName) {
        if (isCourseTypeExist(syllabusName, courseTypeName) == false) {
            return getCourseTypes(syllabusName);
        }
        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\"" +
                        courseTypeName + "\"]"
        );
        return getCourseTypes(syllabusName);
    }
}
