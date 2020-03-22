package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.xmlValidator.XMLValidatorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServices {

    @Autowired
    private BaseXServices baseXServices;

    @Autowired
    private SyllabusServices syllabusServices;

    private static final XMLValidatorServices xmlValidatorServices = new XMLValidatorServices();


    public Boolean checkExistence(
            String syllabusName,
            Integer yearId,
            Integer semesterId,
            String courseCode
    ) {
        if (!syllabusServices
                .isSyllabusExists(syllabusName)) return false;
        if (!syllabusServices
                .isYearExistsInSyllabus(syllabusName, yearId)) return false;
        if (!syllabusServices
                .isSemesterExistsInYear(syllabusName, yearId, semesterId)) return false;
        if (!syllabusServices
                .isCourseExistsInSemester(syllabusName, yearId, semesterId, courseCode)) return false;
        return true;
    }

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param courseCode
     * @return course XML
     */
    public String getCourseXML(
            String syllabusName,
            Integer yearId,
            Integer semesterId,
            String courseCode
    ) {
        if (!checkExistence(syllabusName, yearId, semesterId, courseCode)) return null;
        return baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"]//course[@courseCode=\"" + courseCode + "\"]"
        );
    }

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param courseCode
     * @param courseXML
     * @return course XML after updating
     */
    public String updateCourse(
            String syllabusName,
            Integer yearId,
            Integer semesterId,
            String courseCode,
            String courseXML
    ) {
        if (!checkExistence(syllabusName, yearId, semesterId, courseCode)) return null;
        if (!xmlValidatorServices.isValidCourseXMLForUpdating(courseXML, courseCode)) return null;
        baseXServices.write(
                "replace node //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"]//course[@courseCode=\"" + courseCode + "\"]" +
                        "with " + courseXML
        );
        return getCourseXML(syllabusName, yearId, semesterId, courseCode);
    }
}
