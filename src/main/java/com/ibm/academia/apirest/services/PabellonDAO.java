package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>
{
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon);
}
