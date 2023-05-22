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
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.Documentos.DocumentoAtualizador;
import com.autobots.automanager.modelo.Documentos.DocumentoCreate;
import com.autobots.automanager.modelo.Documentos.DocumentoSelecionador;
import com.autobots.automanager.modelo.Endereco.EnderecoAtualizador;
import com.autobots.automanager.modelo.Endereco.EnderecoSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
public class EnderecoController {
    @Autowired
	private ClienteRepositorio repositorioCliente;
	@Autowired
	private EnderecoRepositorio repositorioEndereco;
	@Autowired
	private EnderecoSelecionador selecionador;


	
	@GetMapping("/endereco/{id}") // funciona
	public Endereco obterEndereco(@PathVariable long id) {
		List<Endereco> enderecos = repositorioEndereco.findAll();
		return selecionador.selecionar(enderecos, id);
	}


	@GetMapping("/enderecos") // funciona
	public List<Endereco> obterEnderecos() {
		List<Endereco> enderecos = repositorioEndereco.findAll();
		return enderecos;
	}

	@PostMapping("/cadastroEndereco") //  funciona
	public void cadastrarDocumentos(@RequestBody Cliente cliente) {
		Cliente cliente2 = repositorioCliente.getById(cliente.getId());
		cliente2.setEndereco(cliente.getEndereco());
		repositorioCliente.save(cliente2);
	} 

	@PutMapping("/atualizarEndereco") // funciona
	public void atualizarDocumentos(@RequestBody Cliente atualizacao) {
        Cliente cliente = repositorioCliente.getById(atualizacao.getId());
        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(cliente.getEndereco(), atualizacao.getEndereco());
        repositorioCliente.save(cliente);
	}

	@DeleteMapping("/excluirEndereco/{id}") // funciona
	public void excluirDocumento(@PathVariable long id) {
		Endereco endereco = repositorioEndereco.getById(id);
		Cliente cliente = repositorioCliente.findByEnderecoId(id);
        cliente.remove(endereco);
        repositorioCliente.save(cliente);	
	}
}
