package dao;

import core.Database;
import entity.Cart;
import entity.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CartDao {
    private Connection connection;
    private ProductDao productDao;
    private CustomerDao customerDao;

    public CartDao() {
        this.connection = Database.getInstance();
        this.productDao=new ProductDao();
        this.customerDao=new CustomerDao();
    }

    public ArrayList<Cart> findAll() {
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT*FROM cart");
            while (rs.next()) {
                carts.add(this.match(rs));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carts;
    }

    public boolean save(Cart cart) { //buraya bir bak hata olabilir son video 7.45dk
        String query = "INSERT INTO cart " +
                "(" +
                "id," +
                "customer_id," +
                "product_id," +
                "price," +
                "date," +
                "note"+
                ")" +
                "VALUES (?,?,?,?,?,?)";


        try {
            PreparedStatement pr = this.connection.prepareStatement(query);

            pr.setInt(1, cart.getId());
            pr.setInt(2, cart.getCustomerId());
            pr.setInt(3, cart.getProductId());
            pr.setInt(4, cart.getPrice());
            pr.setDate(5, Date.valueOf(cart.getDate()));
            pr.setString(6, cart.getNote());

            return pr.executeUpdate() != -1;


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;

    }

    public Cart match(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId((rs.getInt("id")));
        cart.setPrice(rs.getInt("price"));
        cart.setCustomerId(rs.getInt("customer_id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setNote(rs.getString("note"));
        cart.setDate(LocalDate.parse(rs.getString("date")));
        cart.setCustomer(this.customerDao.getById(cart.getCustomerId()));
        cart.setProduct(this.productDao.getById(cart.getProductId()));
        return cart;
    }

}