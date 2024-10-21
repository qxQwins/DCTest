package qwins.dctest.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sku")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private int price;

    @Column(name = "article")
    private int article;

    @Column(name = "status")
    private String status;

    public SKU() {
    }

    public SKU(Long id, int price, int article, String status) {
        this.id = id;
        this.price = price;
        this.article = article;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SKU{" +
                "id=" + id +
                ", price=" + price +
                ", article=" + article +
                ", status='" + status + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SKU sku = (SKU) o;
        return price == sku.price && article == sku.article && Objects.equals(id, sku.id) && Objects.equals(product, sku.product) && Objects.equals(status, sku.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, price, article, status);
    }
}
