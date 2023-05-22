package com.autobots.automanager.controles;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;

import com.autobots.automanager.modelo.Documentos.DocumentoAtualizador;
import com.autobots.automanager.modelo.Documentos.DocumentoCreate;
import com.autobots.automanager.modelo.Documentos.DocumentoSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
public class DocumentosControle {
    @Autowired
	private ClienteRepositorio repositorio;
	@Autowired
	private DocumentoRepositorio repositorioDocumento;
	@Autowired
	private DocumentoSelecionador selecionador;
	@Autowired
	private DocumentoCreate criador;

	


	@GetMapping("/documento/{id}") // funciona
	public Documento obterDocumento(@PathVariable long id) {
		List<Documento> documentos = repositorioDocumento.findAll();
		return selecionador.selecionar(documentos, id);
	}

	@GetMapping("/documentos") // funciona
	public List<Documento> obterDocumentos() {
		List<Documento> documentos = repositorioDocumento.findAll();
		return documentos;
	}

	@PostMapping("/cadastroDocumentos") // funciona
	public void cadastrarDocumentos(@RequestBody Cliente cliente) {
		Cliente cliente2 = repositorio.getById(cliente.getId());
		cliente2.getDocumentos().addAll(cliente.getDocumentos());
		repositorio.save(cliente2);
	} 

	@PutMapping("/atualizarDocumentos") // funciona
	public void atualizarDocumentos(@RequestBody Cliente atualizacao) {
        Cliente cliente = repositorio.getById(atualizacao.getId());
        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(cliente.getDocumentos(), atualizacao.getDocumentos());
        repositorio.save(cliente);
	}
	@DeleteMapping("/excluirDocumento/{id}") // funciona
	public void excluirDocumento(@PathVariable long id) {
		Documento documento = repositorioDocumento.getById(id);
		Cliente cliente = repositorio.findByDocumentosId(id);
		cliente.getDocumentos().remove(documento);
		repositorio.save(cliente);	
	}
}
