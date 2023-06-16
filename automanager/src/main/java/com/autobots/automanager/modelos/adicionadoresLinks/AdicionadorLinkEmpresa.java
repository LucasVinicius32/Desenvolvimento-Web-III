package com.autobots.automanager.modelos.adicionadoresLinks;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ControllerEmpresa;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.modelos.AdicionadorLink;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<Empresa> {

  @Override
  public void adicionarLink(List<Empresa> lista) {
    for (Empresa empresa : lista) {
      long id = empresa.getId();
      Link linkProprio = WebMvcLinkBuilder
          .linkTo(WebMvcLinkBuilder
              .methodOn(ControllerEmpresa.class)
              .obterEmpresa(id))
          .withSelfRel();
      empresa.add(linkProprio);
    }

  }

  @Override
  public void adicionarLink(Empresa empresa) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(ControllerEmpresa.class)
            .obterEmpresas())
        .withRel("empresas");
    empresa.add(linkProprio);
  }

}
