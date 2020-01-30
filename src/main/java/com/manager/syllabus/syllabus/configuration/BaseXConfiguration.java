package com.manager.syllabus.syllabus.configuration;

import com.manager.syllabus.syllabus.repository.BaseXRepository;
import com.manager.syllabus.syllabus.service.basex.BaseXServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseXConfiguration {

    private static final String DATABASE_NAME = "syllabus";

    private BaseXServices baseXServices;
    private static BaseXRepository baseXRepository;

    @Bean
    public BaseXServices baseXServices(){
        baseXRepository = new BaseXRepository();
        baseXRepository.startBaseXServer(DATABASE_NAME);

        this.baseXServices = new BaseXServices(baseXRepository.getSession());
        return this.baseXServices;
    }
}
