package ar.edu.davinci.dvds20201cg1.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_id")
	private Long id;
	
	@Column(name = "pro_nombre")
	@NotBlank(message = "*El nombre es obligatorio")
	@NotEmpty(message = "*Por favor ingrese su nombre")
	@NotNull
	@Size(min=2, max=45)
	private String name;
	
	@Column(name = "pro_precio")
	@NotBlank(message = "*El precio es obligatorio")
	@NotEmpty(message = "*Por favor ingrese el precio")
	@NotNull
	private BigDecimal price;
}
