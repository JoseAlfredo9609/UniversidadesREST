package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController 
{
	@Autowired
	private AulaDAO aulaDao;
	
	/**
	 * Endpoint para buscar todos los objetos de tipo aula
	 * @return Retorna una lista de objetos tipo aula
	 * BadRequestException En caso de que falle buscando objetos de tipo aula
	 * @author JAMR - 10/12/2021
	 */
	@GetMapping("/aulas")
	public ResponseEntity<?> buscartodos()
	{
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		
		if(aulas.isEmpty())
			throw new BadRequestException("No existen clientes");
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);  
	}
	
	/**
	 * Endpoint para buscar un aula
	 * @param aulaId Parametro para buscar aula
	 * @return Retorna un objeto tipo aula
	 * BadRequestException En caso de que falle buscando a un ojeto tipo aula.
	 * author JAMR - 08/12/2021
	 */
	@GetMapping("/id/{aulaId}")
	public ResponseEntity<?> buscarAulaPorId(@PathVariable Integer aulaId) 
	{
		Optional<Aula> oAula =  aulaDao.buscarPorId(aulaId);		
		
		if(!oAula.isPresent())
			throw new BadRequestException(String.format("El aula con ID %d noexiste",aulaId));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para crear un aula
	 * @param aula Parametro para buscar aula
	 * @param result Parametro para buscar errores
	 * @return Un objeto tipo aula creado
	 * ListaErrores En caso de que falle creando un objeto tipo aula
	 * author JAMR - 08/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> guardarAula(@Valid @RequestBody Aula aula, BindingResult result) 
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para actualizar un objeto del tipo aula
	 * @param aulaId Parámetro para buscar el aula
	 * @param aula Objeto con la informacion a modificar
	 * @return Retorna un objeto tipo aula con la informacion actualizada
	 * NotFoundException En caso de que falle actualizando el objeto tipo aula
	 * author JAMR - 08/12/2021
	 */
	@PutMapping("/upd/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula )
	{
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("Cliente con ID: %d no existe", aulaId));
		
		Aula aulaActualizada = aulaDao.actualizar(oAula.get(), aula);
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para eliminar cliente
	 * @param clienteId Parametro para buscar cliente
	 * @return Retornar respuesta de objeto tipo cliente eliminado
	 * NotFoundException En caso de que falle eliminando el objeto cliente
	 * author JAMR - 08/12/2021
	 */
	@DeleteMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		Optional<Aula> cliente = aulaDao.buscarPorId(aulaId);
		
		if(!cliente.isPresent())
			throw new NotFoundException(String.format("Aula con ID: %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		respuesta.put("OK", "Aula ID: " + aulaId + " eliminado exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
}

