package com.test.qpassessment.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name = "PERMISSION")
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(name = "permission_enable", nullable = false)
    private boolean permissionEnable;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permission")
    private List<Role> role;

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPermissionEnable() {
        return permissionEnable;
    }

    public void setPermissionEnable(boolean permissionEnable) {
        this.permissionEnable = permissionEnable;
    }

    @Override
    public String getAuthority() {
        return id.toString();
    }

}
