package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>
{
	public Optional<Pabellon> findPabellonBynombre(String nombre);
	
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon);
}
