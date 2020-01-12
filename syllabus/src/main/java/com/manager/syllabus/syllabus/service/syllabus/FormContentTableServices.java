package com.manager.syllabus.syllabus.service.syllabus;

import com.manager.syllabus.syllabus.model.bundle.CourseInputForm;
import com.manager.syllabus.syllabus.model.contents.TableRow;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import com.manager.syllabus.syllabus.service.jaxb.JAXBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Most of Null pointer exceptions are handled in CourseInputFormServices
 */
@Service
public class FormContentTableServices {

    @Autowired
    private BaseXServices baseXServices;

    private JAXBServices jaxbServices = new JAXBServices();

    private static final Integer FIRST_INDEX_OF_TABLE_ROW = 1;

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param fieldId
     * @return
     */
    public Boolean doesFieldExistInTable(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer fieldId
    ) {
        return baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\""
                        + sectionSerialId + "\"]//fields//field[" + fieldId + "])"
        ).equals("true");
    }


    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     */
    public void addNewFieldInTableOfFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {

        baseXServices.write(
                "insert node  <field>New Field</field> as last into //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\""
                        + sectionSerialId + "\"]//fields"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param fieldId
     */
    public void deleteFieldFromTableInFormSectionBySerialNo(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer fieldId
    ) {
        baseXServices.write(
                "delete node //syllabus[@name=\""
                        + syllabusName + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\""
                        + sectionSerialId + "\"]//fields//field[" + fieldId + "]"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public Integer getCountOfFieldsInTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        return Integer.parseInt(
                baseXServices.read(
                        "count(//syllabus[@name=\"" + syllabusName
                                + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                                + sectionSerialId + "\"]//table[@contentId=\""
                                + sectionSerialId + "\"]//fields//field)"
                )
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public Integer getCountOfRowsInTable(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        return Integer.parseInt(
                baseXServices.read(
                        "count(//syllabus[@name=\"" + syllabusName
                                + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                                + sectionSerialId + "\"]//table[@contentId=\""
                                + sectionSerialId + "\"]//rows//row)"
                )
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    public Integer getIdOfLastRowInTable(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        return Integer.parseInt(
                baseXServices.read(
                        "data(//syllabus[@name=\"" + syllabusName
                                + "\"]//courseType[@name=\"" + courseTypeName
                                + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                                + sectionSerialId + "\"]//table[@contentId=\""
                                + sectionSerialId + "\"]//rows//*[last()]//@tableRowId)"
                )
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @return
     */
    private TableRow createNewTableRow(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        Integer countOfFields = getCountOfFieldsInTableInFormSection(
                syllabusName, courseTypeName, sectionSerialId
        );
        TableRow newTableRow = new TableRow();
        newTableRow.formRow(countOfFields);

        Integer countOfRows = getCountOfRowsInTable(
                syllabusName, courseTypeName, sectionSerialId
        );
        if (countOfRows == 0) {
            newTableRow.setTableRowId(FIRST_INDEX_OF_TABLE_ROW);
        } else {
            newTableRow.setTableRowId(
                    getIdOfLastRowInTable(
                            syllabusName, courseTypeName, sectionSerialId
                    ) + 1
            );
        }
        return newTableRow;
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     */
    public void addNewRowInTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId
    ) {
        TableRow newTableRow = createNewTableRow(
                syllabusName, courseTypeName, sectionSerialId
        );

        baseXServices.write(
                "insert node "
                        + jaxbServices.objectToXmlString(newTableRow, false)
                        + " as last into //syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\"" + sectionSerialId + "\"]//rows"
        );
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param rowId
     * @return
     */
    public Boolean doesRowExistInTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer rowId
    ) {
        return baseXServices.read(
                "exists(//syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\"" + sectionSerialId
                        + "\"]//rows//row[@tableRowId=\"" + rowId + "\"])"
        ).equals("true");
    }

    /**
     * @param syllabusName
     * @param courseTypeName
     * @param sectionSerialId
     * @param rowId
     */
    public void deleteRowFromTableInFormSection(
            String syllabusName, String courseTypeName, Integer sectionSerialId, Integer rowId
    ) {
        baseXServices.write(
                "delete node //syllabus[@name=\"" + syllabusName
                        + "\"]//courseType[@name=\"" + courseTypeName
                        + "\"]//courseInputForm//courseInputFormSection[@serialId=\""
                        + sectionSerialId + "\"]//table[@contentId=\"" + sectionSerialId
                        + "\"]//rows//row[@tableRowId=\"" + rowId + "\"]"
        );
    }
}
