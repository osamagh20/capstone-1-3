package com.example.capstone13.Service;

import com.example.capstone13.Model.MerchantStock;
import com.example.capstone13.Repository.MerchantRepository;
import com.example.capstone13.Repository.MerchantStockRepository;
import com.example.capstone13.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;


    public List<MerchantStock> getMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public Integer addMerchantStock(MerchantStock merchantStock){
        for (int i = 0; i < productRepository.findAll().size(); i++) {
            if(merchantStock.getProduct_id().equals(productRepository.findAll().get(i).getId())){
                for (int j=0; j<merchantRepository.findAll().size(); j++) {
                    if (merchantStock.getMerchant_id().equals(merchantRepository.findAll().get(j).getId())) {
                        merchantStockRepository.save(merchantStock);
                        return 1;
                    }
                }
                return 2;
            }
        }
        return 3;
    }

    public Boolean updateMerchantStock(Integer id,MerchantStock merchantStock){
        MerchantStock oldMerchantStock = merchantStockRepository.getById(id);
        if (oldMerchantStock != null) {
            oldMerchantStock.setProduct_id(merchantStock.getProduct_id());
            oldMerchantStock.setMerchant_id((merchantStock.getMerchant_id()));
            oldMerchantStock.setStock(merchantStock.getStock());
            merchantStockRepository.save(merchantStock);
        }
        return false;
    }

    public Boolean deleteMerchantStock(Integer id){
        MerchantStock deleteMerchantStock = merchantStockRepository.getById(id);
        if (deleteMerchantStock != null) {
            merchantStockRepository.delete(deleteMerchantStock);
            return true;
        }
        return false;
    }

    public Boolean addStock(Integer merstid,Integer merid,Integer prodid,Integer additionalStock){
        MerchantStock olddmerchantStock = merchantStockRepository.getById(merstid);

        for (int i = 0; i < merchantStockRepository.findAll().size(); i++) {
            if (merchantStockRepository.findAll().get(i).getMerchant_id().equals(merid)) {
                if (merchantStockRepository.findAll().get(i).getProduct_id().equals(prodid)) {
                    olddmerchantStock.setStock(olddmerchantStock.getStock() + additionalStock);
                    merchantStockRepository.save(merchantStockRepository.findAll().get(i));
                    return true;
                }
            }
        }
        return false;
    }

}
