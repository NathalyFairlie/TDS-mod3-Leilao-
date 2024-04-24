/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() {
        // Inicialize a conexão aqui
        this.conn = new conectaDAO().connectDB(); // Assumindo que a classe ConectaDAO esteja corretamente definida
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,'A Venda')";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> nomeListaProdutos = new ArrayList<>();
        String sql = "Select * From produtos";
        try (Connection conn = new conectaDAO().connectDB(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int valor = rs.getInt("valor");
                String status = rs.getString("status");
                ProdutosDTO produto = new ProdutosDTO();
                nomeListaProdutos.add(produto);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listagem;
    }

    public void updateStatus(int id) {
        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
