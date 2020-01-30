package com.manager.syllabus.syllabus.model.bundle;

import com.manager.syllabus.syllabus.model.contents.Table;
import com.manager.syllabus.syllabus.model.contents.TextArea;
import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "course")
@XmlType(propOrder = {
        "courseTitle",
        "courseCredit",
        "courseType",
        "semesterId",
        "yearId",
        "textAreaList",
        "tableList"
})
public class Course {
    private String courseCode;
    private String courseTitle;
    private Double courseCredit;
    private String courseType;
    private Integer semesterId;
    private Integer yearId;

    private List<TextArea> textAreaList = new ArrayList<>();
    private List<Table> tableList = new ArrayList<>();

    @XmlAttribute(name = "courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    @XmlElement(name = "content")
    public List<TextArea> getTextAreaList() {
        return textAreaList;
    }

    @XmlElement(name = "content")
    public List<Table> getTableList() {
        return tableList;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Double getCourseCredit() {
        return courseCredit;
    }

    public String getCourseType() {
        return courseType;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public Integer getYearId() {
        return yearId;
    }
}
