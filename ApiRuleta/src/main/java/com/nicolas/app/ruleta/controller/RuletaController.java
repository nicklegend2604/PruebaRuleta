package com.nicolas.app.ruleta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nicolas.app.ruleta.models.entity.Apuesta;
import com.nicolas.app.ruleta.models.entity.Ruleta;
import com.nicolas.app.ruleta.models.service.RuletaService;
import com.nicolas.app.ruleta.util.Constantes;

@RestController
public class RuletaController {

	@Autowired
	private RuletaService ruletaService;

	@GetMapping("/")
	public List<Ruleta> listar() {
		return ruletaService.encontrarTodos();
	}

	@PostMapping("/")
	public Map<String, Long> crear(@RequestBody Ruleta ruleta) {
		Map<String, Long> respuesta = new HashMap<>();
		Long idRuleta = ruletaService.crear(ruleta);
		respuesta.put(Constantes.ID_RULETA, idRuleta);
		return respuesta;
	}

	@PutMapping("/abrir/{id}")
	public Map<String, Object> abrir(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		Boolean exitoso;
		String mensaje = Constantes.MSG_OK;
		try {
			ruletaService.manejarEstadoRuleta(true, id);
			exitoso = true;
		} catch (Exception e) {
			exitoso = false;
			mensaje = e.getMessage();
		}
		respuesta.put(Constantes.EXITOSO, exitoso);
		respuesta.put(Constantes.MENSAJE, mensaje);
		return respuesta;
	}
	
	@PutMapping("/cerrar/{id}")
	public Map<String, Object> cerrar(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		Boolean exitoso;
		String mensaje = Constantes.MSG_OK;
		try {
			List<Apuesta> apuestas = ruletaService.encontrarApuestasPorId(id);
			ruletaService.manejarEstadoRuleta(false, id);
			respuesta.put(Constantes.APUESTAS, apuestas);
			exitoso = true;
		} catch (Exception e) {
			exitoso = false;
			mensaje = e.getMessage();
		}
		respuesta.put(Constantes.EXITOSO, exitoso);
		respuesta.put(Constantes.MENSAJE, mensaje);
		return respuesta;
	}

	@PutMapping("/apostar/{id}")
	public Map<String, Object> apostar(@RequestHeader("idUsuario") Long idUsuario, @RequestBody Apuesta apuesta,
			@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		apuesta.setIdUsuario(idUsuario);
		Boolean exitoso;
		String mensaje = Constantes.MSG_OK;
		try {
			ruletaService.apostar(id, apuesta);
			exitoso = true;
		} catch (Exception e) {
			exitoso = false;
			mensaje = e.getMessage();
		}
		respuesta.put(Constantes.EXITOSO, exitoso);
		respuesta.put(Constantes.MENSAJE, mensaje);
		return respuesta;
	}

}
