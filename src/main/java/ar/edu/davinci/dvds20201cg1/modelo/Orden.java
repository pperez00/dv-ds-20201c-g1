package ar.edu.davinci.dvds20201cg1.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ord_id")
	private Long id;
	
	@ManyToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL)
	@JoinColumn(name="ord_cli_id", referencedColumnName="cli_id")
	private Cliente client;
	
	
    @OneToMany(targetEntity = OrdenItem.class, mappedBy = "orden")
    private List<OrdenItem> items;
    
	@Column(name = "ord_fecha")
	@NotBlank(message = "*La fecha es obligatorio")
	@NotEmpty(message = "*Por favor ingrese la fecha")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dates;
	
	
	public void agregarOrdenItem(OrdenItem ordenItem) {
		if(this.items == null) {
			this.items = new ArrayList<OrdenItem>();
		}
		this.items.add(ordenItem);
	}
	
	public void removerOrdenItem(OrdenItem ordenItem) {
		if (this.items.contains(ordenItem)) {
			this.items.remove(ordenItem);
		}
	}

}

