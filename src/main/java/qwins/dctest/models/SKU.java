package qwins.dctest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
