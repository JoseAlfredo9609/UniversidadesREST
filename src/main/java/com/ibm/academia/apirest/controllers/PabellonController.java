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
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController
{
	@Autowired
	private PabellonDAO pabellonDao;
	
	@GetMapping("/lista/pabellones")
	public List<Pabellon> buscarTodas()
	{
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		if(pabellones.isEmpty())
			throw new BadRequestException("No existen pabellones");
		
		return pabellones;
	}
	
	@GetMapping("/id/{pabellonId}")
	public Pabellon buscarPabellonPorId(@PathVariable Integer pabellonId)
	{

		Pabellon pabellon = pabellonDao.buscarPorId(pabellonId).orElse(null);
		if(pabellon == null)
			throw new BadRequestException(String.format("La carrera con ID: %d no existe", pabellonId));
		
		return pabellon;
	}
	
	@PostMapping
	public ResponseEntity<?> guardarPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result)
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
		Pabellon pabellonguardado = pabellonDao.guardar(pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonguardado, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para actualizar un objeto de tipo pabellon
	 * @param pabellonId Parámetro para buscar el pabellon
	 * @param pabellon Objeto con la información a modificar
	 * @return Retorna un objeto de tipo Pabellon con la información actualizada
	 * @NotFoundException En caso de que falle actualizando el objeto pabellon
	 * @author NDSC - 06/12/2021
	 */
	@PutMapping("/upd/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", pabellonId));
		
		Pabellon pabellonActualizado = pabellonDao.actualizar(oPabellon.get(), pabellon); 
		
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK); 
	}
	
	@DeleteMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		Optional<Pabellon> pabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!pabellon.isPresent())
			throw new NotFoundException(String.format("La carrera con ID: %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(pabellonId);
		respuesta.put("OK", "Carrera ID: " + pabellonId + " eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	}
	
	
}
