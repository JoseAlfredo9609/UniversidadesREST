package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO
{
	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	public Aula actualizar(Aula aulaEncontrada, Aula aula) {
		Aula aulaActualizada = null;
		aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());
		aulaEncontrada.setPizarron(aula.getPizarron());
		return aulaActualizada;
	}	
	
}
