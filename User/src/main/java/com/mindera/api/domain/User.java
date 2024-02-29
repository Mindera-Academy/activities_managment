package com.mindera.api.domain;

import com.mindera.api.model.Gender;
import com.mindera.api.model.Kind;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String docNumber;
    @Column
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(1)")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(7)")
    private Kind kind;
    @Embedded
    private Contact contact;

    public User() {
    }

    public User(Integer id, String name, String docNumber, Integer age, Gender gender, Kind kind, Contact contact) {
        this.id = id;
        this.name = name;
        this.docNumber = docNumber;
        this.age = age;
        this.gender = gender;
        this.kind = kind;
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String username) {
        this.docNumber = docNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void  setAge(Integer age) {
        this.age = age;
    }

    public Enum getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


}