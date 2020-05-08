package com.nicolas.app.ruleta.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicolas.app.ruleta.models.dao.RuletaDao;
import com.nicolas.app.ruleta.models.entity.Apuesta;
import com.nicolas.app.ruleta.models.entity.Ruleta;
import com.nicolas.app.ruleta.util.Constantes;

@Service
public class RuletaServiceImpl implements RuletaService {

	@Autowired
	private RuletaDao ruletaDao;

	@Override
	@Transactional
	public Long crear(Ruleta ruleta) {
		return ruletaDao.save(ruleta).getId();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ruleta> encontrarTodos() {
		return (List<Ruleta>) ruletaDao.findAll();
	}



	@Override
	@Transactional
	public void apostar(Long idRuleta, Apuesta apuesta) throws Exception {
		if (apuesta.getCantidad() > Constantes.APUESTA_MAXIMA)
			throw new Exception(Constantes.MSG_APUESTA_MAXIMA);

		Ruleta ruleta = ruletaDao.findById(idRuleta).orElse(null);

		if (ruleta == null)
			throw new Exception(Constantes.MSG_RULETA_NO_ENCONTRADA);

		if (ruleta.getAbierta()) {
			ruleta.getApuestas().add(apuesta);
			ruletaDao.save(ruleta);
		} else
			throw new Exception(Constantes.MSG_RULETA_CERRADA);

	}


	@Override
	@Transactional
	public void manejarEstadoRuleta(Boolean abierto, Long id) throws Exception {
		Ruleta ruleta = ruletaDao.findById(id).orElse(null);
		if (ruleta != null)
			ruleta.setAbierta(abierto);
		else
			throw new Exception(Constantes.MSG_RULETA_NO_ENCONTRADA);
	}

	@Override
	public List<Apuesta> encontrarApuestasPorId(Long id) throws Exception {
		Ruleta ruleta = ruletaDao.findById(id).orElse(null);
		if (ruleta != null)
			return ruleta.getApuestas();
		else
			throw new Exception(Constantes.MSG_RULETA_NO_ENCONTRADA);
	}

}
