package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.model.bundle.Course;
import com.manager.syllabus.syllabus.service.syllabus.SyllabusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "syllabus/edit")
public class SyllabusEditorController {

    @Autowired
    private SyllabusServices syllabusServices;

    @GetMapping("/{syllabusName}/add/year")
    public String addYear(
            @PathVariable("syllabusName") String syllabusName
    ) {
        return syllabusServices.addYear(syllabusName);
    }

    @DeleteMapping("/{syllabusName}/delete/year/{yearId}")
    public String deleteYear(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId
    ) {
        return syllabusServices.deleteYearById(syllabusName, yearId);
    }

    @GetMapping("/{syllabusName}/{yearId}/add/semester")
    public String addSemesterIntoYear(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId
    ) {
        return syllabusServices.addSemesterIntoYear(syllabusName, yearId);
    }

    @DeleteMapping("/{syllabusName}/{yearId}/delete/semester/{semesterId}")
    public String deleteSemesterFromYear(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId,
            @PathVariable("semesterId") Integer semesterId
    ) {
        return syllabusServices.deleteSemesterFromYear(syllabusName, yearId, semesterId);
    }

    @PostMapping("/{syllabusName}/{yearId}/{semesterId}/add/course")
    public String addCourseIntoSemester(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId,
            @PathVariable("semesterId") Integer semesterId,
            @RequestBody Course course
    ) {
        return syllabusServices.addCourseIntoSemester(syllabusName, yearId, semesterId, course);
    }

    @DeleteMapping("/{syllabusName}/{yearId}/{semesterId}/delete/course/{courseCode}")
    public String deleteCourseFromSemesterByCourseCode(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId,
            @PathVariable("semesterId") Integer semesterId,
            @PathVariable("courseCode") String courseCode
    ) {
        return syllabusServices.deleteCourseFromSemesterByCourseCode(syllabusName, yearId, semesterId, courseCode);
    }
}
