package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.model.bundle.CourseInputForm;
import com.manager.syllabus.syllabus.service.syllabus.CourseInputFormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "syllabus/create_form")
public class CourseInputFromCreatorController {

    @Autowired
    private CourseInputFormServices courseInputFormServices;

    @GetMapping("/{syllabusName}/{courseTypeName}/add_new_section")
    public CourseInputForm addNewFormSection(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName
    ) {
        return courseInputFormServices.addNewFormSection(
                syllabusName, courseTypeName
        );
    }

    @DeleteMapping("{syllabusName}/{courseTypeName}/delete_section/{sectionSerialId}")
    public CourseInputForm deleteFormSectionBySectionSerialId(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId
    ) {
        return courseInputFormServices.deleteFormSectionBySectionSerialId(
                syllabusName, courseTypeName, sectionSerialId
        );
    }

    @GetMapping("{syllabusName}/{courseTypeName}/{sectionSerialId}/change_selected/{selectedContent}")
    public CourseInputForm changeSelectedContentOfFormSection(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId,
            @PathVariable("selectedContent") String selectedContent
    ) {
        return courseInputFormServices.changeSelectedContentOfFormSection(
                syllabusName, courseTypeName, sectionSerialId, selectedContent
        );
    }

    /*--------------------------------------------------------------------------*/

    /**
     * Methods for for Table
     */

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    @GetMapping("{syllabusName}/{courseTypeName}/{sectionSerialId}/add_field_in_table")
    public CourseInputForm addNewFieldInTableOfFormSection(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId
    ) {
        return courseInputFormServices.addNewFieldInTableOfFormSection(
                syllabusName, courseTypeName, sectionSerialId
        );
    }

    @DeleteMapping("{syllabusName}/{courseTypeName}/{sectionSerialId}/delete_table_field/{fieldId}")
    public CourseInputForm deleteFieldFromTableInFormSectionBySerialNo(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @PathVariable("sectionSerialId") Integer sectionSerialId,
            @PathVariable("fieldId") Integer fieldId
    ) {
        return courseInputFormServices.deleteFieldFromTableInFormSectionBySerialNo(
                syllabusName, courseTypeName, sectionSerialId, fieldId
        );
    }
    /*--------------------------------------------------------------------------*/

    @PostMapping("{syllabusName}/{courseTypeName}/auto_save")
    public CourseInputForm autoSaveFormStructure(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName,
            @RequestBody CourseInputForm courseInputForm
    ) {
        return courseInputFormServices.saveOrUpdateFormStructure(
                syllabusName, courseTypeName, courseInputForm
        );
    }
}
