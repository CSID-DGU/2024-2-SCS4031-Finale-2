package Backend.src.main.java.com.finale.product.entity;

import com.finale.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String content;
	@ManyToOne
	@JoinColumn(name = "writer_id", nullable = false)
	private User writer;
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
}