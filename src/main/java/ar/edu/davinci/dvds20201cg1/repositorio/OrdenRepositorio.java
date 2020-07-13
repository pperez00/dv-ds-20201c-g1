package ar.edu.davinci.dvds20201cg1.repositorio;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20201cg1.modelo.Orden;


@Repository("ordenRepositorio")
public interface OrdenRepositorio extends JpaRepository<Orden, Long>{

	//public List<Orden> searchByName(@Param("nombre") String name) throws DataAccessException;

}


