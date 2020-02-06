package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.model.bundle.CourseInputForm;
import com.manager.syllabus.syllabus.model.contents.CourseInputFormSection;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseInputFormServices {

    @Autowired
    private BaseXServices baseXServices;

    @Autowired
    private CourseTypeServices courseTypeServices;

    @Autowired
    private FormContentTableServices formContentTableServices;

    private JAXBServices jaxbServices = new JAXBServices();

    private static final Integer FIRST_INDEX_OF_FORM_SECTIONS = 101;
    private static final List<String> CONTENT_NAMES = new ArrayList<>(Arrays.asList("Table", "TextArea"));

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

    /**
     * @param syllabusName
     * @param courseTypeName
     * @return
     */
    public CourseInputForm getCourseInputForm(String syllabusName, String courseTypeName) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }
        return (CourseInputForm) jaxbServices.xmlStringToObject(
                baseXServices.read(
                        "//syllabus[@name=\""
                                + syllabusName + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm"
                ), new CourseInputForm());
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
                                + "\"]//courseInputForm//courseInputFormSections//*[last()]//@serialId)"
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
    public CourseInputForm addNewFormSection(String syllabusName, String courseTypeName) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }

        Integer newSectionSerialNo = getNewFormSectionSerialNo(syllabusName, courseTypeName);
        CourseInputFormSection newFormSection = new CourseInputFormSection(newSectionSerialNo);

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(newFormSection, false)
                        + " into //syllabus[@name=\"" + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]//courseInputForm//courseInputFormSections"
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
    public CourseInputForm deleteFormSectionBySectionSerialId(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }

        if (doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false) {
            getCourseInputForm(syllabusName, courseTypeName);
        }

        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSections//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]"
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }


    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param selectedContent
     * @return
     */
    public CourseInputForm changeSelectedContentOfFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId, String selectedContent
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false ||
                doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false ||
                CONTENT_NAMES.contains(selectedContent) == false) {
            return null;
        }

        baseXServices.write(
                "delete node //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//selectedContent"
        );

        baseXServices.write(
                "insert node <selectedContent>"
                        + selectedContent + "</selectedContent> as first into //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]"
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }


    /************************************************************************************************************/
    /***Methods for Table***/


    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param fieldId
     * @return
     */
    public CourseInputForm deleteFieldFromTableInFormSectionBySerialNo(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer fieldId
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }
        if (doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false ||
                formContentTableServices.doesFieldExistInTable(
                        syllabusName, courseTypeName, sectionSerialId, fieldId) == false) {
            return getCourseInputForm(syllabusName, courseTypeName);
        }

        formContentTableServices.deleteFieldFromTableInFormSectionBySerialNo(
                syllabusName, courseTypeName, sectionSerialId, fieldId
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public CourseInputForm addNewFieldInTableOfFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }
        if (doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false) {
            return getCourseInputForm(syllabusName, courseTypeName);
        }

        formContentTableServices.addNewFieldInTableOfFormSection(
                syllabusName, courseTypeName, sectionSerialId
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }


    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public CourseInputForm addNewRowInTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false ||
                doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false) {
            return null;
        }
        formContentTableServices.addNewRowInTableInFormSection(
                syllabusName, courseTypeName, sectionSerialId
        );
        return getCourseInputForm(syllabusName, courseTypeName);
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param rowId
     * @return
     */
    public CourseInputForm deleteRowFromTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer rowId
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false ||
                doesFormSectionExist(syllabusName, courseTypeName, sectionSerialId) == false ||
                formContentTableServices.doesRowExistInTableInFormSection(
                        syllabusName, courseTypeName, sectionSerialId, rowId) == false) {
            return null;
        }

        formContentTableServices.deleteRowFromTableInFormSection(
                syllabusName, courseTypeName, sectionSerialId, rowId
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }
    /************************************************************************************************************/


    /**
     * @param syllabusName
     * @param courseTypeName
     * @param courseInputForm
     * @return
     */
    public CourseInputForm saveOrUpdateFormStructure(
            String syllabusName, String courseTypeName, CourseInputForm courseInputForm
    ) {
        if (courseTypeServices.doesCourseTypeExist(syllabusName, courseTypeName) == false) {
            return null;
        }

        baseXServices.write(
                "delete node //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]//courseInputForm"
        );

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(courseInputForm, false)
                        + " into //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\""
                        + courseTypeName + "\"]"
        );

        return getCourseInputForm(syllabusName, courseTypeName);
    }


}
