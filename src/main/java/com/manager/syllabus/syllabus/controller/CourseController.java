package com.manager.syllabus.syllabus.controller;


import com.manager.syllabus.syllabus.service.syllabus.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/syllabus/course")
public class CourseController {

    @Autowired
    private CourseServices courseServices;

    /**
     * @param syllabusName
     * @param yearId
     * @param semesterId
     * @param courseCode
     * @return course XML string
     */
    @GetMapping("/get/{syllabusName}/{yearId}/{semesterId}/{courseCode}")
    public String getCourseXML(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId,
            @PathVariable("semesterId") Integer semesterId,
            @PathVariable("courseCode") String courseCode
    ) {
        return courseServices.getCourseXML(
                syllabusName,
                yearId,
                semesterId,
                courseCode
        );
    }

    @PostMapping("/update/{syllabusName}/{yearId}/{semesterId}/{courseCode}")
    public String updateCourse(
            @PathVariable("syllabusName") String syllabusName,
            @PathVariable("yearId") Integer yearId,
            @PathVariable("semesterId") Integer semesterId,
            @PathVariable("courseCode") String courseCode,
            @RequestBody String courseXML
    ) {
        return courseServices.updateCourse(
                syllabusName,
                yearId,
                semesterId,
                courseCode,
                courseXML
        );
    }
}
