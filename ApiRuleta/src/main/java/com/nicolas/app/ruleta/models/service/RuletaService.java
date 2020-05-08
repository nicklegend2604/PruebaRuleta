package com.nicolas.app.ruleta.models.service;

import java.util.List;

import com.nicolas.app.ruleta.models.entity.Apuesta;
import com.nicolas.app.ruleta.models.entity.Ruleta;

public interface RuletaService {

	public Long crear(Ruleta ruleta);

	public List<Ruleta> encontrarTodos();

	public void apostar(Long idRuleta, Apuesta apuesta) throws Exception;

	public void manejarEstadoRuleta(Boolean abierto, Long id) throws Exception;

	public List<Apuesta> encontrarApuestasPorId(Long id) throws Exception;
}
