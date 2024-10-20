package qwins.dctest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.dctest.models.SKU;
import qwins.dctest.repositories.SKURepository;

import java.util.List;

@Service
public class SKUService {

    private final SKURepository skuRepository;
    @Autowired
    SKUService(SKURepository skuRepository) {
        this.skuRepository = skuRepository;
    }
    public List<SKU> findAll() {
        return skuRepository.findAll();
    }
    public SKU findById(Long id) {
        return skuRepository.findById(id).orElse(null);
    }
    public SKU save(SKU sku) {
        return skuRepository.save(sku);
    }
    public void delete(SKU sku) {
        skuRepository.delete(sku);
    }
}
