package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.service.syllabus.CourseInputFormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{syllabusName}/{courseTypeName}/delete_section/{sectionSerialId}")
    public String deleteFormSectionBySectionSerialId(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId
    ) {
        return courseInputFormServices.deleteFormSectionBySectionSerialId(
                syllabusName, courseTypeName, sectionSerialId
        );
    }

    @GetMapping("{syllabusName}/{courseTypeName}/{sectionSerialId}/change_selected/{selectedContent}")
    public String changeSelectedContentOfFormSection(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId,
            @PathVariable("selectedContent") String selectedContent
    ) {
        return courseInputFormServices.changeSelectedContentOfFormSection(
                syllabusName, courseTypeName, sectionSerialId, selectedContent
        );
    }
}
