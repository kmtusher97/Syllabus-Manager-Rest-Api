<a href="https://spring.io/projects/spring-boot"><img src="https://spring.io/images/spring-initializr-4291cc0115eb104348717b82161a81de.svg" height="100px" /></a>
<a href="https://basex.org/"><img src="https://basex.org/images/basex.svg" height="100px" /></a>
<br>

# Syllabus Manager Rest Api . ![framework](https://img.shields.io/badge/spring--boot-2.2.2.RELEASE-green) ![maven](https://img.shields.io/badge/maven-3.6.0-orange)  ![java](https://img.shields.io/badge/java-8-blue) 
A RESTful API to create syllabus. It allows to create curse types(Theory, Laboratory, Viva etc) and corresponding input forms for the courses. Syllabus is stored as XML. The CRUD operation in the XML Database is handled with BaseX.

## BaseX . ![basex](https://img.shields.io/badge/basex-9.0-red) ![xml](https://img.shields.io/badge/-XML-red) ![xquery](https://img.shields.io/badge/XQuery-3.1-red)
BaseX is a robust, high-performance XML database engine and a highly compliant XQuery 3.1 processor with full support of the W3C Update and Full Text extensions. It serves as excellent framework for building complex data-intensive web applications.
It comes with interactive user interfaces (desktop, web-based) that give you great insight into your data. <a href="https://basex.org/">more..</a>

## BaseX Configuration & Service Methods
- <a href="https://github.com/kmtusher97/Syllabus-Manager-Rest-Api/blob/master/src/main/java/com/manager/syllabus/syllabus/configuration/BaseXConfiguration.java">Configure BaseX </a> 
- <a href="https://github.com/kmtusher97/Syllabus-Manager-Rest-Api/blob/master/src/main/java/com/manager/syllabus/syllabus/service/basex/BaseXServices.java">Query on XML database using BaseX</a> 


<b>1. Syllabus Creator API's</b>  
>- Get a syllabus by syllabus name(as XML)  
    **method:** `GET` 
    **url:** `localhost:8081/api/syllabus/get/{syllabusName}` 
>- Add a year in the the syllabus  
    **method:** `GET` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/add/year` 
>- Delete a year from syllabus by yearId  
    **method:** `DELTE` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/delete/year/{yearId}`
>- Add semester in year  
    **method:** `GET`   
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/add/semester` 
>- Delete semester from a year  
    **method:** `DELTE`   
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/delete/semester/{semesterId}`   
>- Add course(course code) in a semester  
**method:** `POST`   
**url:** `localhost:8081/api/syllabus/edit/{syllabusName}/{yearId}/{semesterId}/add/course`  
 **data:** `{"courseCode": "CSE 101"}`
>- Get a syllabus by syllabus name(as XML)  
    **method:** `DELETE` 
    **url:** `localhost:8081/syllabus/edit/{syllabusName}/{yearId}/{semesterId}/delete/course/{courseCode}` 

    
