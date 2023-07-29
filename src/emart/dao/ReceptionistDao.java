/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ReceptionistPojo;
import emart.pojo.UserPojo;
//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.*;
import java.sql.*;


public class ReceptionistDao {
    public static Map<String ,String> getNonRegisteredReceptionist() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empid, empname from employees where job='Receptionist' and empid   not in (select empid from users where usertype='Receptionist')");
        HashMap<String,String> hashMapList=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            hashMapList.put(id, name);
        }
        return hashMapList;
       }
    public static boolean addReceptionist(UserPojo user) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into users values(?,?,?,?,?)");
        ps.setString(1,user.getUserid());
        ps.setString(2, user.getEmpid());
        ps.setString(3,user.getPassword());
        ps.setString(4,user.getUsertype());
        ps.setString(5,user.getUsername());
        int result=ps.executeUpdate();
        return result==1;
        
    }
    public static  List<ReceptionistPojo> getAllReceptionistDetails() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select users.empid, empname,userid,job,salary from users, employees where usertype='Receptionist' and users.empid=employees.empid");
        ArrayList<ReceptionistPojo> al= new ArrayList<>();
        while(rs.next()){
            ReceptionistPojo recep=new ReceptionistPojo();
            recep.setEmpid(rs.getString(1));
            recep.setEmpname(rs.getString(2));
            recep.setUserid(rs.getString(3));
            recep.setJob(rs.getString(4));
            recep.setSalary(rs.getString(5));
            al.add(recep);
            }
        return al;
        }
    public static Map<String,String> getAllReceptionistId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select userid,username from users where usertype='Receptionist'  order by empid");
        HashMap<String,String> receptionList= new HashMap<>();
        while(rs.next()){
            receptionList.put(rs.getString(1), rs.getString(2));
        }
        return receptionList;
        
    }
    public static boolean updateReceptionist(String userid, String pwd)  throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps= conn.prepareStatement("update users  set password=? where userid=?");
        ps.setString(1, pwd);
        ps.setString(2,userid);
        return ps.executeUpdate()==1;
        
        
    }
    public static List<String> getAllReceptionistUserId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select userid from users  where userType='Receptionist' ");
        ResultSet rs=ps.executeQuery();
        List<String> list=new ArrayList<>();
        while(rs.next()){
            list.add(rs.getString(1));
            
        }
        return list;
      }
    public static boolean deleteReceptionist(String userid) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
        ps.setString(1,userid);
       
        return ps.executeUpdate()==1;
    }
}
