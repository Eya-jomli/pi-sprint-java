/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pidev.services;
import java.sql.*;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pidev.entities.Produit;


/**
 *
 * @author Eya
 */
public class ServiceProduit {
    private Connection conn;

    public ServiceProduit(Connection conn) {
        this.conn = conn;
    }

    public void createProduit(Produit produit) throws SQLException {
        String sql = "INSERT INTO `edu4u`.`produits` (name, description, price, stock, created_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produit.getName());
            stmt.setString(2, produit.getDescription());
            stmt.setDouble(3, produit.getPrice());
            stmt.setInt(4, produit.getStock());
            stmt.setTimestamp(5, new Timestamp(produit.getCreated_at().getTime()));
            stmt.executeUpdate();
        }
    }

    public Produit readProduit(int id) throws SQLException {
        String sql = "SELECT * FROM produits WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produit(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                            rs.getDouble("price"), rs.getInt("stock"), rs.getTimestamp("created_at"));
                } else {
                    return null;
                }
            }
        }
    }

    public void updateProduit(Produit produit) throws SQLException {
        String sql = "UPDATE produits SET name = ?, description = ?, price = ?, stock = ?, created_at = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produit.getName());
            stmt.setString(2, produit.getDescription());
            stmt.setDouble(3, produit.getPrice());
            stmt.setInt(4, produit.getStock());
            stmt.setTimestamp(5, new Timestamp(produit.getCreated_at().getTime()));
            stmt.setInt(6, produit.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteProduit(int id) throws SQLException {
        String sql = "DELETE FROM produits WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Produit> listProduits() throws SQLException {
        String sql = "SELECT * FROM produits";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            List<Produit> produits = new ArrayList<>();
            while (rs.next()) {
                produits.add(new Produit(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("stock"), rs.getTimestamp("created_at")));
            }
            return produits;
        }
    }
}
