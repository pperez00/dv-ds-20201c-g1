package ar.edu.davinci.dvds20201cg1.repositorio;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20201cg1.modelo.Producto;


@Repository("productosRepositorio")
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

	public List<Producto> searchByName(@Param("nombre") String name) throws DataAccessException;


}
