package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.service.BaseXServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyllabusServices {

    @Autowired
    private BaseXServices baseXServices;

    /**
     * @param syllabusName
     * @return full syllabus as XML string
     */
    public String getSyllabusBySyllabusName(String syllabusName) {
        return baseXServices.read(
                "//syllabus[@name=\"" + syllabusName + "\"]"
        );
    }

    public void addYear(String syllabusName) {
    }
}
