package lms.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private StateAndCity stateAndCity;


    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StateAndCity getStateAndCity() {
        return stateAndCity;
    }

    public void setStateAndCity(StateAndCity stateAndCity) {
        this.stateAndCity = stateAndCity;
    }

    public Address(long addressId, String address, StateAndCity stateAndCity) {
        this.addressId = addressId;
        this.address = address;
        this.stateAndCity = stateAndCity;
    }

    public Address() {

    }

}

