package com.manager.syllabus.syllabus.controller;


import com.manager.syllabus.syllabus.service.syllabus.SyllabusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "syllabus")
public class SyllabusController {

    @Autowired
    private SyllabusServices syllabusServices;

    @GetMapping("/get/{syllabusName}")
    public String getSyllabusBySyllabusName(@PathVariable("syllabusName") String syllabusName) {
        return syllabusServices.getSyllabusBySyllabusName(syllabusName);
    }

}
