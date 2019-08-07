package com.example.springbootmongodb.enty;

import org.springframework.data.annotation.Id;

/**
 * @author lala
 */
public class Customer {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Id
    public String id;
    public String firstName;
    public String lastName;

    public Customer() {

    }
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastNaame='%s']", id, firstName, lastName);
    }

}
