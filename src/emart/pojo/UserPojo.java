/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.pojo;


public class UserPojo {
    
private String userid;
private String empid;
private String password;
private String usertype;
private String username;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmpid() {
        return empid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }

}
