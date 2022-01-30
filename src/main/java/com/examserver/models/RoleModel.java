package com.examserver.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class RoleModel {

    @Id
    private Long roleId;
    private String roleName;

    //a single role can be alloted to many user
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "role")
    private Set<UserRoleModel> userRoles = new HashSet<>();

    public RoleModel() {
    }

    public RoleModel(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserRoleModel> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleModel> userRoles) {
        this.userRoles = userRoles;
    }
}
