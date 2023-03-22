package com.gestionmdp.gestionmdp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordDao extends Dao<Password> {
    private final Connection connection;
    public PasswordDao(){
        //connection a la base de données
        connection = ConnextionBdd.getInstance().connection;
    }
    public List<Password> findByUserID(int userId){
        List<Password> passwords = new ArrayList<>();

        try{
            PreparedStatement passwordStatement = connection.prepareStatement("select * from passwordcompte where user_id = ?");
            passwordStatement.setInt(1, userId);

            ResultSet result = passwordStatement.executeQuery();
            while (result.next()){
                Password password = new Password();
                password.setNom(result.getString("comptename"));
                password.setMdp(result.getString("mdpcompte"));
                passwords.add(password);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return passwords;
    }

    public void addMdpCompte(String nom, String mdp, int userId){
        try{
            PreparedStatement statement = connection.prepareStatement("insert into passwordCompte (mdpCompte, compteName, user_id) values (?,?,?)");
            statement.setString(1, mdp);
            statement.setString(2, nom);
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Password find(int id) {
        return null;
    }

    @Override
    public ArrayList<Password> findAll() {
        ArrayList<Password> passwords = new ArrayList<>();
        try{
            //execution de la requete SQL pour récupéré tous les mots de passes
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from passwordCompte");
            while (result.next()) {
                String name = result.getString("name");
                String passwordCompte = result.getString("mdpcompte");

                Password password = new Password(name, passwordCompte);
                passwords.add(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passwords;
    }
}
