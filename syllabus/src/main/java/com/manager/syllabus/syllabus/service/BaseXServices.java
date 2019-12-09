package com.manager.syllabus.syllabus.service;

import lombok.NoArgsConstructor;
import org.basex.api.client.ClientSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


@NoArgsConstructor
public class BaseXServices {

    /**
     * baseX server starts on default port 1984
     */
    private ClientSession session;

    @Autowired
    public BaseXServices(ClientSession session) {
        this.session = session;
    }

    /**
     * performs read query
     *
     * @param xQuery
     * @return an XML string or null
     */
    public String read(String xQuery) {
        String result = null;
        try {
            result = session.execute("XQUERY " + xQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * performs write query
     *
     * @param xQuery
     */
    public void write(String xQuery) {
        try {
            session.execute("XQUERY " + xQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
