package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.model.bundle.CourseInputForm;
import com.manager.syllabus.syllabus.model.contents.CourseInputFormSection;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInputFormServices {

    @Autowired
    private BaseXServices baseXServices;

    @Autowired
    private CourseTypeServices courseTypeServices;

    private JAXBServices jaxbServices = new JAXBServices();

    private static final Integer FIRST_INDEX_OF_FORM_SECTIONS = 101;

    /**
     * @param syllabusName
     * @param courseTypeName
     */
    public void createInitialForm(String syllabusName, String courseTypeName) {
        CourseInputForm courseInputForm = new CourseInputForm();

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(courseInputForm, false)
                        + " into //syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]"
        );
    }

    public String getCourseInputForm(String syllabusName, String courseTypeName) {
        return baseXServices.read(
                "//syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public Integer getFormSectionsCount(String syllabusName, String courseTypeName) {
        return Integer.parseInt(baseXServices.read(
                "count (//syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection)"
                )
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public Integer getSerialIdOfLastFormSection(String syllabusName, String courseTypeName) {
        return Integer.parseInt(
                baseXServices.read(
                        "data(//syllabus[@name=\"" + syllabusName
                                + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm//*[last()]//@serialId)"
                )
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public Integer getNewFormSectionSerialNo(String syllabusName, String courseTypeName) {
        Integer formSectionsCount = getFormSectionsCount(syllabusName, courseTypeName);
        if (formSectionsCount == 0) {
            return FIRST_INDEX_OF_FORM_SECTIONS;
        }
        return getSerialIdOfLastFormSection(syllabusName, courseTypeName) + 1;
    }


    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public String addNewFormSection(String syllabusName, String courseTypeName) {
        if (courseTypeServices.isCourseTypeExist(syllabusName, courseTypeName) == false) {
            return getCourseInputForm(syllabusName, courseTypeName);
        }

        Integer newSectionSerialNo = getNewFormSectionSerialNo(syllabusName, courseTypeName);
        CourseInputFormSection newFormSection = new CourseInputFormSection(newSectionSerialNo);

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(newFormSection, false)
                        + " into //syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]//courseInputForm"
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public Boolean doesFormSectionExist(String syllabusName, String courseTypeName, Integer sectionSerialId) {
        return (
                baseXServices.read(
                        "exists(//syllabus[@name=\"" + syllabusName
                                + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                                + sectionSerialId + "\"])"
                ).equals("true")
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public String deleteFormSectionBySectionSerialId(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        if (courseTypeServices.isCourseTypeExist(syllabusName, courseTypeName) == false) {
            return getCourseInputForm(syllabusName, courseTypeName);
        }
        if (doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false) {
            return getCourseInputForm(syllabusName, courseTypeName);
        }

        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]"
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }


}
