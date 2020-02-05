package com.manager.syllabus.syllabus.model;

import com.manager.syllabus.syllabus.model.bundle.Year;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Syllabus {
    private String syllabusName;
    private Integer effectiveFrom;
    private Integer effectiveTo;
    private String syllabusType;
    private List<Year> years = new ArrayList<>();
}
