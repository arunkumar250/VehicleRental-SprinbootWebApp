package com.rentalapp.model;


public class LoginDto {
    
    private String userName;
    
    private String password;
    
    private int roleId;
    
    // Constructors, getters, and setters

    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public LoginDto() {
    }

    public LoginDto(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }
     
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
    public String toString() {
        return "LoginDto{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

