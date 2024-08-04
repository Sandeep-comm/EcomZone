package com.ecomzone.ecomzone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private UserAddress address;

	@Column(nullable = false)
	private Double totalPrice;

	@Column(name = "order_date", nullable = false)
	private LocalDateTime orderDate;
}
