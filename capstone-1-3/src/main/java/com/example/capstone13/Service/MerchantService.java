package com.example.capstone13.Service;

import com.example.capstone13.Model.Merchant;
import com.example.capstone13.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(Integer id,Merchant merchant){
        Merchant oldMerchant = merchantRepository.getById(id);
        if(oldMerchant != null){
            oldMerchant.setName(merchant.getName());
            merchantRepository.save(oldMerchant);
            return true;
        }
        return false;
    }

    public Boolean deleteMerchant(Integer id){
        Merchant deleteMerchant = merchantRepository.getById(id);
        if(deleteMerchant != null){
            merchantRepository.delete(deleteMerchant);
            return true;
        }

        return false;
    }


}
