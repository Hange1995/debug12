package com.hardworking.training.jdbc;

import com.hardworking.training.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerJDBCDAO {
    static final String DBURL = "jdbc:postgresql://localhost:5430/dealer";
    static final String USER = "admin";
    static final String PASS = "password";


    public List<Player> getPlayers() {
        List<Player> pLayers = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM player";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                double weight = rs.getDouble("weight");
                double hight = rs.getDouble("hight");
                String team = rs.getString("team");
                long position_id = rs.getLong("position_id");
                //Fill the object
                Player player = new Player();
                player.setId(id);
                player.setName(name);
                player.setFirst_name(first_name);
                player.setLast_name(last_name);

                pLayers.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return pLayers;

    }

    public static void main(String[] args) {
        PlayerJDBCDAO playerJDBCDAO = new PlayerJDBCDAO();
        System.out.println(playerJDBCDAO.getPlayers());
        logger.info("infomation");
        logger.debug("debug");
        logger.warn("WARNING");
    }
    private static Logger logger = LoggerFactory.getLogger(PlayerJDBCDAO.class);

}

