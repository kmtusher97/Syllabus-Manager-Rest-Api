
# Syllabus-Manager-Rest-Api  
  
### 1. Syllabus Creator Services  
- Get a syllabus by syllabus name(as XML)  
    **method:** `GET` 
    **url:** `localhost:8081/api/syllabus/get/{syllabusName}` 
- Add a year in the the syllabus  
    **method:** `GET` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/add/year` 
- Delete a year from syllabus by yearId  
    **method:** `DELTE` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/delete/year/{yearId}`
- Add semester in year  
    **method:** `GET`   
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/add/semester` 
- Delete semester from a year  
    **method:** `DELTE`   
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/delete/semester/{semesterId}`   
- Add course(course code) in a semester  
**method:** `POST`   
**url:** `localhost:8081/api/syllabus/edit/{syllabusName}/{yearId}/{semesterId}/add/course`  
 **data:** `{"courseCode": "CSE 101"}`
- Get a syllabus by syllabus name(as XML)  
    **method:** `DELETE` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/{semesterId}/delete/course/{courseCode}` 

    