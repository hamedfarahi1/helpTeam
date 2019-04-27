package com.kharazmi.helpdesk.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Role", schema = "HelpDeskV2", catalog = "")
public class RoleModel {
    private int id;
    private String name;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel roleModel = (RoleModel) o;
        return id == roleModel.id &&
                Objects.equals(name, roleModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
