package com.manager.syllabus.syllabus.controller;

import com.manager.syllabus.syllabus.service.syllabus.SyllabusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "syllabus/edit")
public class SyllabusEditorController {

    @Autowired
    private SyllabusServices syllabusServices;

    @GetMapping("/{syllabusName}/add/year")
    public String addYear(
            @PathVariable("syllabusName") String syllabusName
    ) {
        syllabusServices.addYear(syllabusName);
        return "added";
    }
}
