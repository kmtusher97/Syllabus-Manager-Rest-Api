package com.manager.syllabus.syllabus.model.bundle;

import com.manager.syllabus.syllabus.model.contents.CourseInputFormSection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.ArrayList;


@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "courseInputForm")
public class CourseInputForm {

    private List<CourseInputFormSection> courseInputFormSections = new ArrayList<>();

    @XmlElementWrapper(name = "courseInputFormSections")
    @XmlElement(name = "courseInputFormSection")
    public List<CourseInputFormSection> getCourseInputFormSections() {
        return courseInputFormSections;
    }

    /**
     * @param courseInputFormSection
     */
    public void addCourseInputFormSection(CourseInputFormSection courseInputFormSection) {
        if (this.courseInputFormSections.isEmpty()) {
            this.courseInputFormSections = new ArrayList<>();
        }
        this.courseInputFormSections.add(courseInputFormSection);
    }
}
