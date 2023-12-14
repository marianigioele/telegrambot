import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class DB {

        private Connection conn;


        public DB() {
            connetti();
        }

        private void connetti() {
            String db = "jdbc:mysql://127.0.0.1:3306/davedere";
            String username = "root";
            String password = "";

            try {
                conn = DriverManager.getConnection(db, username, password);
                if (conn != null)
                    System.out.println("connessione avvenuta");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public StringBuilder select() {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            try {
                if (!conn.isValid(5)) {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String query = "SELECT * FROM davedere";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    result.add(new ArrayList<>(Arrays.asList(rs.getString("chatId"), rs.getString("name"))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            StringBuilder output = new StringBuilder("");
            for (ArrayList<String> r : result) {
                output.append(r.get(0));
                output.append("\t\t");
                output.append(r.get(1));
                output.append("\t\t");
                output.append("\n");
            }

            return output;
        }

        public String aggiungiFilm(String chatId, String name) {
            try {
                if (!conn.isValid(5)) {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            String query = "INSERT INTO davedere(chatId, name) VALUES(?,?)";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, chatId);
                statement.setString(2, name);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return "Inserimento non avvenuto";
            }



            return "Inserimento avvenuto con successo";
        }

        public String Elimina(String chatId, String name) {
            try {
                if (!conn.isValid(5)) {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            String query = "DELETE FROM davedere WHERE chatId LIKE ? AND name LIKE ?;";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, chatId);
                statement.setString(2, name);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return "rimozione non avvenuta";
            }



            return "rimozione avvenuta con successo";
        }
    }
