package api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.rest.model.Cidade;
import api.rest.repositoy.CidadeRepository;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository estadoRepository;

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Cidade>> estados() {

		List<Cidade> lista = estadoRepository.findAll();

		return new ResponseEntity<List<Cidade>>(lista, HttpStatus.OK);

	}

}

