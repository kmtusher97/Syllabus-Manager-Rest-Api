package com.manager.syllabus.syllabus.model.contents;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "contentBundle")
public class ContentBundle implements Serializable {

    private Integer id;

    private Integer selected;

    private TextArea textArea;

    private Table table;

    /**
     * Future Contents will be added here
     */

    @XmlAttribute(name = "id")
    public Integer getId() {
        return id;
    }


}
