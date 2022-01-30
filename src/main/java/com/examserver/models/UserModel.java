package com.examserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private String profileImage;
    private boolean enabled = true;

    //an user can have many role
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "user")
    @JsonIgnore
    private Set<UserRoleModel> userRoles = new HashSet<>();

    public UserModel() {
    }

    public UserModel(Long userId, String userName, String password, String firstName, String lastName,
                     String emailId, String contactNumber, String profileImage, boolean enabled) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.profileImage = profileImage;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userID) {
        this.userId = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoleModel> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleModel> userRoles) {
        this.userRoles = userRoles;
    }
}
