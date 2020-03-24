package com.pavelz.geoTagApplication.entity;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(appliesTo = "geoTag")
public class GeoTag implements Serializable {
    private static final long serialVersionUID = 1299756033445859L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "xPosition")
    private int xPosition;

    @Column(name = "yPosition")
    private int yPosition;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    public GeoTag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp createdDate) {
        this.creationDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xPosition='" + xPosition + '\'' +
                ", yPosition='" + yPosition + '\'' +
                ", createdDate=" + creationDate +
                '}';
    }
}
