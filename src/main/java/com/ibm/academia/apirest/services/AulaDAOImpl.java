package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Iterable<Aula> buscarAulaPorNombrePabellon(String nombre) 
	{
		return repository.buscarAulaPorNombrePabellon(nombre);
	}
	
	@Override
	public Optional<Aula> findAulaByNombre(String nombre) 
	{
		return repository.findAulaByNombre(nombre);
	}

	@Override
	@Transactional
	public Aula actualizar(Aula aulaEncontrada, Aula aula) 
	{
		Aula aulaActualizada = null;
		aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());
		aulaEncontrada.setPizarron(aula.getPizarron());
		return aulaActualizada;
	}

			
}
