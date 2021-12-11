package com.ibm.academia.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer> 
{
	public Optional<Aula> findAulaByNombre(String nombre);
	
	@Query("select a from Aula a join fetch a.pabellon p where p.nombre = ?1")
	public Iterable<Aula> buscarAulaPorNombrePabellon(String nombre);

}
