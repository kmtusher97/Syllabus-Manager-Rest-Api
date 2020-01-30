package com.manager.syllabus.syllabus.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.basex.BaseXServer;
import org.basex.api.client.ClientSession;
import org.basex.core.cmd.CreateDB;

import java.io.IOException;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseXRepository {

    private final int SERVER_PORT = 1984;
    private final String SERVER_HOST = "localhost";
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin";
    private final String STORAGE_LOCATION = "src/main/resources/xml/";
    private final String XML_EXTENSION = ".xml";

    private BaseXServer server;
    private ClientSession session;

    /**
     * Starts the server
     * baseX server starts on default port 1984
     *
     * @param databaseName
     */
    public void startBaseXServer(String databaseName) {
        try {
            server = new BaseXServer();

            try {
                session = new ClientSession(
                        SERVER_HOST,
                        SERVER_PORT,
                        ADMIN_USERNAME,
                        ADMIN_PASSWORD
                );
                session.execute(
                        new CreateDB(
                                databaseName,
                                STORAGE_LOCATION + databaseName + XML_EXTENSION
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
