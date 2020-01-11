package com.manager.syllabus.syllabus.message;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
    private String status;
    private Object data;
}
