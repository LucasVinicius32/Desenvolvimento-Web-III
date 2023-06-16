package com.autobots.automanager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.enumeracoes.TipoDocumento;
import com.autobots.automanager.repositorios.EmpresaRepositorio;

@SpringBootApplication
public class AutomanagerApplication implements CommandLineRunner {

	@Autowired
	private EmpresaRepositorio repositorioEmpresa;

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder toCriptografy = new BCryptPasswordEncoder();
		String senhaEncriptografa = toCriptografy.encode("1234");

		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Ford");
		empresa.setNomeFantasia("Auto Parts");
		empresa.setCadastro(new Date());

		Endereco enderecoEmpresa = new Endereco();
		enderecoEmpresa.setEstado("São Paulo");
		enderecoEmpresa.setCidade("São Paulo");
		enderecoEmpresa.setBairro("Mooca");
		enderecoEmpresa.setRua("Rua do Comércio");
		enderecoEmpresa.setNumero("123");
		enderecoEmpresa.setCodigoPostal("03111-000");

		empresa.setEndereco(enderecoEmpresa);

		Telefone telefoneEmpresa = new Telefone();
		telefoneEmpresa.setDdd("011");
		telefoneEmpresa.setNumero("987654321");

		empresa.getTelefones().add(telefoneEmpresa);

		Usuario admin = new Usuario();
		admin.setNome("Administrador");
		admin.setNomeSocial("admin");
		admin.getPerfis().add(PerfilUsuario.ADMINISTRADOR);

		admin.setEmail("admin@empresa.com");
		admin.setSenha(senhaEncriptografa);

		Endereco enderecoAdmin = new Endereco();
		enderecoAdmin.setEstado("São Paulo");
		enderecoAdmin.setCidade("São Paulo");
		enderecoAdmin.setBairro("Vila Mariana");
		enderecoAdmin.setRua("Avenida Paulista");
		enderecoAdmin.setNumero("456");
		enderecoAdmin.setCodigoPostal("04001-000");

		admin.setEndereco(enderecoAdmin);

		empresa.getUsuarios().add(admin);

		Telefone telefoneAdmin = new Telefone();
		telefoneAdmin.setDdd("011");
		telefoneAdmin.setNumero("987654321");

		admin.getTelefones().add(telefoneAdmin);

		Documento cpfAdmin = new Documento();
		cpfAdmin.setDataEmissao(new Date());
		cpfAdmin.setNumero("12345678901");
		cpfAdmin.setTipo(TipoDocumento.CPF);

		admin.getDocumentos().add(cpfAdmin);

		repositorioEmpresa.save(empresa);


	}
}
