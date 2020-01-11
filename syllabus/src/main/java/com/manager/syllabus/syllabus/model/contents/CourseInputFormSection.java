package com.manager.syllabus.syllabus.model.contents;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "courseInputFormSection")
public class CourseInputFormSection {
    private Integer serialId;

    private String selectedContent;

    private TextArea textArea;

    private Table table;

    /**
     * Future Contents will be added here
     */


    @XmlAttribute(name = "serialId")
    public Integer getSerialId() {
        return serialId;
    }

    public CourseInputFormSection(Integer serialId) {
        this.serialId = serialId;
        this.selectedContent = "TextArea"; /*TextArea, Table*/
        this.textArea = new TextArea();
        this.table = new Table();

        this.textArea.getInitialTextArea(serialId);
        this.table.getInitialTable(serialId);
    }


}
