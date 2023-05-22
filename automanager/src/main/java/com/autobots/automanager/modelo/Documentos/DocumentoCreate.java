package com.autobots.automanager.modelo.Documentos;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.StringVerificadorNulo;
// import com.autobots.automanager.modelo.StringVerificadorNulo;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Component
public class DocumentoCreate {
    @Autowired
	private ClienteRepositorio repositorio;

    private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private List <Documento> DadosEnviar = new ArrayList<Documento>();

    
    
	public void cadastrar(Cliente clienteCadastrado, Cliente clienteCadastrar) {
        // List<Documento> documentoCadastrado = clienteCadastrado.getDocumentos();
        // List<Documento> documentoCadastrar = clienteCadastrar.getDocumentos();

        // for (Documento documentocadastrar : documentoCadastrar) {
        //     DadosEnviar.add(documentocadastrar);
            
		// }
        // for (Documento documento : documentoCadastrado) {
        //     DadosEnviar.add(documento);

        // }
        // clienteCadastrado.setDocumentos(DadosEnviar);
        // repositorio.save(clienteCadastrado);
       

	}   
}
