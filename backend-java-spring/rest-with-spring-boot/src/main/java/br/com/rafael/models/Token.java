package br.com.rafael.models;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public Token(String userName, Boolean authenticated, Date created, Date expiration, String accessToken,
            String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Token() {
        //TODO Auto-generated constructor stub
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((authenticated == null) ? 0 : authenticated.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (authenticated == null) {
            if (other.authenticated != null)
                return false;
        } else if (!authenticated.equals(other.authenticated))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (expiration == null) {
            if (other.expiration != null)
                return false;
        } else if (!expiration.equals(other.expiration))
            return false;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        return true;
    }

    

}
