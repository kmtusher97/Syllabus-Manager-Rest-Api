package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.service.syllabus.CourseTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "syllabus/course_type")
public class CourseTypeController {

    @Autowired
    private CourseTypeServices courseTypeServices;

    @GetMapping("/{syllabusName}/get/all")
    public List<String> getAllCourseTypes(@PathVariable("syllabusName") String syllabusName) {
        return courseTypeServices.getAllCourseTypeNames(syllabusName);
    }

    @GetMapping("/{syllabusName}/get/{courseTypeName}")
    public String getCourseTypeXMLByCourseTypeName(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName
    ) {
        return courseTypeServices.getCourseTypeByCourseTypeName(syllabusName, courseTypeName);
    }

    @GetMapping("/edit/{syllabusName}/add/course_type/{courseTypeName}")
    public String addNewCourseType(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName
    ) {
        return courseTypeServices.addNewCourseType(syllabusName, courseTypeName);
    }

    @DeleteMapping("/edit/{syllabusName}/delete/course_type/{courseTypeName}")
    public String deleteCourseTypeByCourseTypeName(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("courseTypeName") String courseTypeName
    ) {
        return courseTypeServices.deleteCourseType(syllabusName, courseTypeName);
    }


}
