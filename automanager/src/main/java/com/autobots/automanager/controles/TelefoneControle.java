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
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.Telefone.TelefoneAtualizador;
import com.autobots.automanager.modelo.Telefone.TelefoneSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
public class TelefoneControle {
    @Autowired
	private ClienteRepositorio repositorioCliente;
	@Autowired
	private TelefoneRepositorio repositorioTelefone;
	@Autowired
	private TelefoneSelecionador selecionador;


	
	@GetMapping("/telefone/{id}") 
	public Telefone obterEndereco(@PathVariable long id) {
		List<Telefone> telefone = repositorioTelefone.findAll();
		return selecionador.selecionar(telefone, id);
	}


	@GetMapping("/telefones") 
	public List<Telefone> obterTelefone() {
		List<Telefone> telefone = repositorioTelefone.findAll();
		return telefone;
	}

	@PostMapping("/cadastroTelefone") 
	public void cadastrarDocumentos(@RequestBody Cliente cliente) {
		Cliente cliente2 = repositorioCliente.getById(cliente.getId());
		cliente2.getTelefones().addAll(cliente.getTelefones());
		repositorioCliente.save(cliente2);
		
	} 

	@PutMapping("/atualizarTelefone") 
	public void atualizarDocumentos(@RequestBody Cliente atualizacao) {
        Cliente cliente = repositorioCliente.getById(atualizacao.getId());
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(cliente.getTelefones(), atualizacao.getTelefones());
        repositorioCliente.save(cliente);
	}

	@DeleteMapping("/excluirTelefone/{id}") 
	public void excluirDocumento(@PathVariable long id) {
		Telefone telefone = repositorioTelefone.getById(id);
		Cliente cliente = repositorioCliente.findByTelefonesId(id);
        cliente.remove(telefone);
        repositorioCliente.save(cliente);	
	}
}
