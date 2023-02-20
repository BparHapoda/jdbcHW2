package org.example.entity;

import java.util.Objects;

public class Abonent extends Entity {
private int id;
    private String name;
    private String surname;

    private String phone;
public Abonent(){

}
    public Abonent( String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abonent abonent)) return false;
        return id == abonent.id && Objects.equals(name, abonent.name) && Objects.equals(surname, abonent.surname) && Objects.equals(phone, abonent.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone);
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

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Abonent{'name='" + name + " , surname=" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static Abonent getAbonent(int id,String name,String surname,String phone){
        Abonent abonent=new Abonent();
        abonent.setId(id);
        abonent.setName(name);
        abonent.setSurname(surname);
        abonent.setPhone(phone);
        return abonent;
    }
}

