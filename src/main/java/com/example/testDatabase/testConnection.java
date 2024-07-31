package com.example.testDatabase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class testConnection {
    private final DataSource dataSource;

    public testConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/checkcon")
    public String checkcon(){
        try {
            Connection con = dataSource.getConnection();
            if(con.isValid(5)){
                return "Connection Is Valid";
            }
            else {
                return "Connection Is In Valid";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
