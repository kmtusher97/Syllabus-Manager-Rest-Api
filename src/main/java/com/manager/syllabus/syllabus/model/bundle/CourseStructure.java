package com.manager.syllabus.syllabus.model.bundle;

import com.manager.syllabus.syllabus.model.contents.ContentBundle;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "courseStructure")
@XmlType(propOrder = {"name", "contentBundleList", "databaseName"})
public class CourseStructure {
    private String name;

    private String databaseName;

    private List<ContentBundle> contentBundleList = new ArrayList<>();

    /**
     * Getters for JAXB xml binding
     */
    @XmlElement(name = "contentBundle")
    public List<ContentBundle> getContentBundleList() {
        return contentBundleList;
    }

}
