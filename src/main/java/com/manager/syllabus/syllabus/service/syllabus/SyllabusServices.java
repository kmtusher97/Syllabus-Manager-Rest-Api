package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.model.bundle.Course;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyllabusServices {

    @Autowired
    private BaseXServices baseXServices;

    private JAXBServices jaxbServices = new JAXBServices();

    /**
     * @param syllabusName
     * @return True if a syllabus exists with name = syllabusName
     */
    public Boolean isSyllabusExists(String syllabusName) {
        return (baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName + "\"])"
        ).equals("true"));
    }

    /**
     * @param syllabusName
     * @return full syllabus as XML string
     */
    public String getSyllabusBySyllabusName(String syllabusName) {
        return baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]"
        );
    }

    /**
     * Finds MEX from an array
     *
     * @param numbers
     * @return
     */
    private Integer getMex(String[] numbers) {
        Integer mex = 1;
        for (String number : numbers) {
            if (number == "") break;
            Integer temporary = Integer.parseInt(number);
            if (temporary == mex) {
                mex++;
                continue;
            }
            break;
        }
        return mex;
    }

    /**
     * adds a year in the syllabus
     *
     * @param syllabusName
     */
    public String addYear(String syllabusName) {
        String[] yearIds = (baseXServices.read(
                "for $nd in //syllabus[@name=\"" + syllabusName + "\"]/year return data($nd/[@id])"
        )).split("\\s+");

        Integer mexYear = getMex(yearIds);

        if (mexYear == 1) {
            baseXServices.write(
                    "insert node <year id=\"" + mexYear + "\"/> as first into //syllabus[@name=\"" +
                            syllabusName + "\"]"
            );
        } else {
            baseXServices.write(
                    "insert node <year id=\"" + mexYear + "\"/> after //syllabus[@name=\"" +
                            syllabusName + "\"]//year[@id=\"" + (mexYear - 1) + "\"]"
            );
        }
        return getSyllabusBySyllabusName(syllabusName);
    }

    /**
     * @param syllabusName
     * @param yearId
     * @return
     */
    public String deleteYearById(String syllabusName, Integer yearId) {
        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId + "\"] "
        );
        return getSyllabusBySyllabusName(syllabusName);
    }

    /**
     * Checks a year exists or not
     *
     * @param syllabusName
     * @param yearId
     * @return
     */
    public Boolean isYearExistsInSyllabus(String syllabusName, Integer yearId) {
        return (baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId + "\"])"
        ).equals("true"));
    }

    /**
     * @param syllabusName
     * @param yearId
     * @return
     */
    public String addSemesterIntoYear(String syllabusName, Integer yearId) {
        if (isYearExistsInSyllabus(syllabusName, yearId) == Boolean.FALSE) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        String[] semesterIds = (baseXServices.read(
                "for $nd in //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester return data($nd/[@id])"
        )).split("\\s+");

        Integer mexSemester = getMex(semesterIds);

        if (mexSemester == 1) {
            baseXServices.write(
                    "insert node <semester id=\"1\"/> as first into //syllabus[@name=\"" + syllabusName +
                            "\"]//year[@id=\"" + yearId + "\"]"
            );
        } else {
            baseXServices.write(
                    "insert node <semester id=\"" + mexSemester + "\"/> after //syllabus[@name=\"" +
                            syllabusName + "\"]//year[@id=\"" + yearId + "\"]//semester[@id=\"" + (mexSemester - 1) + "\"]"
            );
        }
        return getSyllabusBySyllabusName(syllabusName);
    }

    /**
     * check the semester exists or not
     *
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @return
     */
    public Boolean isSemesterExistsInYear(String syllabusName, Integer yearId, Integer semesterId) {
        return (baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"])"
        ).equals("true"));
    }

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @return
     */
    public String deleteSemesterFromYear(String syllabusName, Integer yearId, Integer semesterId) {
        if (isSemesterExistsInYear(syllabusName, yearId, semesterId) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"] "
        );
        return getSyllabusBySyllabusName(syllabusName);
    }

    /**
     * is this course exists
     *
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param courseCode
     * @return
     */
    public Boolean isCourseExistsInSemester(
            String syllabusName,
            Integer yearId,
            Integer semesterId,
            String courseCode
    ) {
        return (baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"]//course[@courseCode=\"" + courseCode + "\"])"
        ).equals("true"));
    }

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param course
     * @return
     */
    public String addCourseIntoSemester(String syllabusName, Integer yearId, Integer semesterId, Course course) {
        if (isYearExistsInSyllabus(syllabusName, yearId) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        if (isSemesterExistsInYear(syllabusName, yearId, semesterId) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        if (isCourseExistsInSemester(syllabusName, yearId, semesterId, course.getCourseCode()) == true) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        baseXServices.write(
                "insert node " + jaxbServices.objectToXmlString(course, false) +
                        " into //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"]"
        );
        return getSyllabusBySyllabusName(syllabusName);
    }

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param courseCode
     * @return
     */
    public String deleteCourseFromSemesterByCourseCode(String syllabusName, Integer yearId, Integer semesterId, String courseCode) {
        if (isYearExistsInSyllabus(syllabusName, yearId) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        if (isSemesterExistsInYear(syllabusName, yearId, semesterId) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        if (isCourseExistsInSemester(syllabusName, yearId, semesterId, courseCode) == false) {
            return getSyllabusBySyllabusName(syllabusName);
        }
        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName + "\"]//year[@id=\"" + yearId +
                        "\"]//semester[@id=\"" + semesterId + "\"]//course[@courseCode=\"" + courseCode + "\"]"
        );
        return getSyllabusBySyllabusName(syllabusName);
    }
}
