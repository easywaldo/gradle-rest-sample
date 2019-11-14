package sample;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "last_name", length = 200)
    private String lastName;

    @Column(name= "first_name", length = 200)
    private String firstName;

    protected Customer() {};

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return  firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
