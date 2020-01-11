package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.service.syllabus.CourseInputFormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "syllabus/create_form")
public class CourseInputFromCreatorController {

    @Autowired
    private CourseInputFormServices courseInputFormServices;

    @GetMapping("/{syllabusName}/{courseTypeName}/add_new_section")
    public String addNewFormSection(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName
    ) {
        return courseInputFormServices.addNewFormSection(
                syllabusName, courseTypeName
        );
    }
}
