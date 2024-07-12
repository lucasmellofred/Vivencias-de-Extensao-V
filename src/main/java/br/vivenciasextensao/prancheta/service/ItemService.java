package br.vivenciasextensao.prancheta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.vivenciasextensao.prancheta.entity.Item;
import br.vivenciasextensao.prancheta.repository.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    
	public Item findById(Long id) {
		return itemRepository.findById(id).orElse(null);
	}
}
