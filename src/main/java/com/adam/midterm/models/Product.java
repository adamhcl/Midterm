package com.adam.midterm.models;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;
	
	//adding quantity
	@Column(name = "quantity", nullable = false)
	@Min(value = 0, message = "*Quantity has to be a non negative number")
	private Integer quantity;
	
	//adding price
	@Column(name = "price", nullable = false)
	@DecimalMin(value = "0.00", message = "*Price has to be non negative number")
	private BigDecimal price;

	public Product() {

	}

	public Product(String name, String description, boolean published, Integer quantity, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.published = published;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	//adding method getQuanity
	public Integer getQuantity() {
		return quantity;
	}
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //adding getPrice
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal unitPrice) {
        this.price = unitPrice;
    }
    


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desc=" + description + ", published=" + published + 
				", quantity=" + quantity + ", price=" + price + "]";
	}
	

}
