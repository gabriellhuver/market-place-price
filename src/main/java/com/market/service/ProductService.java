package com.market.service;

import com.market.contract.dto.CostumerDTO;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.ProductDTO;
import com.market.mapper.CostumerMapper;
import com.market.mapper.ProductMapper;
import com.market.model.Costumer;
import com.market.model.Product;
import com.market.model.RuleMarketPlace;
import com.market.repository.CostumerRepository;
import com.market.repository.ProductRepository;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements GenericService<ProductDTO, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private RuleMarketPlaceRepository ruleMarketPlaceRepository;

    @Override
    public ProductDTO save(final ProductDTO dto) {
        if(dto.getId() != null || dto.getId() == 0)
            dto.setId(null);


        final Product entity = productMapper.toEntity(dto);

        if (dto.getRulesId() != null || !dto.getRulesId().isEmpty()){
            final var rules = new HashSet<RuleMarketPlace>();
            for (Long idRule : dto.getRulesId()) {
                rules.add(ruleMarketPlaceRepository.findById(idRule)
                        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+idRule+", Tipo: "+ RuleMarketPlace.class.getName())));
            }
            entity.setRules(rules);
        }

        final Costumer costumer = costumerRepository.findById(dto.getCostumerId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+dto.getCostumerId()+", Tipo: "+Costumer.class.getName()));

        entity.setCostumer(costumer);
        final var productSave = productRepository.save(entity);
        return productMapper.toDto(productSave);
    }

    @Override
    public List<ProductDTO> findAll(){
        final var products = productRepository.findAll();

        if(products.isEmpty())
            new ObjectNotFoundException("Não existe produtos cadastrados");

        return productMapper.toDto(products);
    }

    @Override
    public ProductDTO find(final Long id) {

        Optional<Product> obj = productRepository.findById(id);

        return productMapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Product.class.getName())));
    }

    @Override
    public ProductDTO update(final ProductDTO dto, final Long id) {
        final Product entity = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException.supply());
        final Product updateEntity = productMapper.updateEntity(entity, dto);
        final Product updatedEntity = productRepository.save(updateEntity);
        return productMapper.toDto(updatedEntity);
    }

    @Override
    public PaginatedResourceDTO<ProductDTO> findPaginate(ProductDTO filter) {
        return null;
    }
}