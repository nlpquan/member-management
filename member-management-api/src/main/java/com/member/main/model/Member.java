package com.member.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "members") // This tells Hibernate to name the table "members"
public class Member {
    @Id // This marks the id as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes the ID auto-increment
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false, precision = 15, scale = 2)
    private BigDecimal salary;

    @Column(name = "join_date", nullable = false)
    @Temporal(TemporalType.DATE) // This specifies that only the date should be stored, without time
    private Date joinDate;

    @Column(name = "age", nullable = false)
    private Integer age;

    // No-argument constructor
    public Member() {
    }

    // All-argument constructor
    public Member(Long id, String name, BigDecimal salary, Date joinDate, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
        this.age = age;
    }

    // Getters and Setters
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                ", age=" + age +
                '}';
    }
}

