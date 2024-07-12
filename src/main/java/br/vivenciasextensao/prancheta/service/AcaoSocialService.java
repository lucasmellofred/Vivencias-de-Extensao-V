package br.vivenciasextensao.prancheta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;
import br.vivenciasextensao.prancheta.projection.AcaoSocialProjection;
import br.vivenciasextensao.prancheta.repository.AcaoSocialRepository;

@Service
public class AcaoSocialService {

    @Autowired
    private AcaoSocialRepository acaoSocialRepository;

    public List<AcaoSocial> findAll() {
        return acaoSocialRepository.findAll();
    }
    
    public List<AcaoSocialProjection> countItensByAcaoSocial() {
        return acaoSocialRepository.countItensByAcaoSocial();
    }
}
