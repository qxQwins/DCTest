package qwins.dctest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.dctest.repositories.SKURepository;

@Service
public class SKUService {
    @Autowired
    private SKURepository skuRepository;
}
