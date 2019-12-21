package com.manager.syllabus.syllabus.model.contents;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "table")
@XmlType(propOrder = {"title", "fields", "rows"})
public class Table implements Serializable {

    private Integer tableId;

    private String title;

    private List<String> fields = new ArrayList<>();

    private List<TableRow> rows = new ArrayList<>();

    public Table(String title, List<String> fields) {
        this.title = title;
        this.fields = fields;
        this.rows = new ArrayList<>();
    }

    /**
     * Getters for xml mapping
     */
    @XmlAttribute(name = "contentId")
    public Integer getTableId() {
        return tableId;
    }

    public String getTitle() {
        return title;
    }

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field")
    public List<String> getFields() {
        return fields;
    }

    @XmlElementWrapper(name = "rows")
    @XmlElement(name = "row")
    public List<TableRow> getRows() {
        return rows;
    }

}
