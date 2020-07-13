package ar.edu.davinci.dvds20201cg1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clientes", uniqueConstraints={@UniqueConstraint(columnNames={"cli_email"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cli_id")
	private Long id;
	
	@Column(name = "cli_nombre")
	@NotBlank(message = "*El nombre es obligatorio")
	@NotEmpty(message = "*Por favor ingrese su nombre")
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	@Column(name = "cli_apellido")
	@NotBlank(message = "*El apellido es obligatorio")
	@NotEmpty(message = "*Por favor ingrese su apellido")
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	
	@Column(name = "cli_email")
	@NotBlank(message = "*El email es obligatorio")
	@NotEmpty(message = "* Por favor ingrese su email")
	@Email(message = "Por favor ingrese un email válido")
	private String email;
	
	@Column(name = "cli_password")
	@NotNull
	@NotBlank(message = "*La password es obligatoria")
	@NotEmpty(message = "* Por favor ingrese su password")
	private String password;

}
