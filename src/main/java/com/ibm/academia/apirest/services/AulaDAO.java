package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Aula;

public interface AulaDAO extends GenericoDAO<Aula>
{
	public Iterable<Aula> buscarAulaPorNombrePabellon(String nombre);
	
	public Optional<Aula> findAulaByNombre(String nombre);
	
	public Aula actualizar(Aula aulaEncontrada, Aula aula);
	
	
}
