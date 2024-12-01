package com.example.capstone13.Controller;

import com.example.capstone13.ApiResponse.ApiResponse;
import com.example.capstone13.Model.MerchantStock;
import com.example.capstone13.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStocks(){
        List<MerchantStock> merchantStocks  = merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        int isAddedMerchantStock = merchantStockService.addMerchantStock(merchantStock);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isAddedMerchantStock ==1){
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock added successfully"));
        } else if (isAddedMerchantStock == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("not have product id or merchant id"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("merchant stock not added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        Boolean isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock  updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        Boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("merchant stock deleted successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @PutMapping("/add-stock/{idms}/{idm}/{idp}/{addStock}")
    public ResponseEntity addStock(@PathVariable Integer idms,@PathVariable Integer idm,@PathVariable Integer idp,@PathVariable Integer addStock){
        Boolean isAdded = merchantStockService.addStock(idms,idm,idp,addStock);

        if(isAdded){
            return ResponseEntity.status(200).body("stock added successfully");
        }
        return ResponseEntity.status(400).body("id not found");

    }

}
