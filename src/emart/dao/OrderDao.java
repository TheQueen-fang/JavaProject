/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class OrderDao {
    public static String getNextOrderId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs= st.executeQuery("select max(p_id) from orders");
        rs.next();
       String orderid=(rs.getString(1));
       if(orderid==null){
           return "O-101";
       }
       int ordno=Integer.parseInt(orderid.substring(1));
        ordno=ordno+1;
       return "O-"+ordno;
        }
    public static boolean addOrder(ArrayList<ProductsPojo> al, String ordId) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders  values(?,?,?,?)");
        int count=0;
        for(ProductsPojo p:al){
            ps.setString(1, ordId);
            ps.setString(2,p.getProductId());
            ps.setInt(3,p.getQuantity());
            ps.setString(4, UserProfile.getUserid());
            //ps.executeUpdate();
            count=count+ps.executeUpdate();
        }
        return count==al.size();
    }
    public static  ArrayList<String> getAllOrder_id() throws SQLException{
        Connection conn=DBConnection.getConnection();
        ArrayList<String> al= new ArrayList<>();
        Statement st= conn.createStatement();
                ResultSet rs= st.executeQuery("select distinct order_id from orders");
                while(rs.next()){
                    al.add(rs.getString(1));
                }
                return al;
    }
    public static List<ProductsPojo> getOrdersDetail(String oid)throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select o.p_id,p_name,p_companyname,p_price,our_price,p_tax,o.quantity from products p,orders o where p.p_id=o.p_id and order_id=? order by o.p_id");
       ps.setString(1,oid);
       ResultSet rs= ps.executeQuery();
       ArrayList<ProductsPojo> oList=new ArrayList<ProductsPojo>();
       while(rs.next())
       {
           ProductsPojo p=new ProductsPojo();
           p.setProductId(rs.getString(1));
           p.setProductName(rs.getString(2));
           p.setProductCompany(rs.getString(3));
           p.setProductPrice(rs.getDouble(4));
           p.setOurPrice(rs.getDouble(5));
           p.setTax(rs.getInt(6));
           p.setQuantity(rs.getInt(7));
           oList.add(p);
       }
       return oList;
    }
}
