package com.example.capstone13.Service;

import com.example.capstone13.Model.User;
import com.example.capstone13.Repository.MerchantRepository;
import com.example.capstone13.Repository.MerchantStockRepository;
import com.example.capstone13.Repository.ProductRepository;
import com.example.capstone13.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantStockRepository merchantStockRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id,User user){
        User oldUser = userRepository.getById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setBalance(user.getBalance());
        oldUser.setItem(user.getItem());
        oldUser.setId(user.getId());
        oldUser.setSubscribe(user.getSubscribe());
        oldUser.setHomeAddress(user.getHomeAddress());
        oldUser.setRole(user.getRole());
        oldUser.setVisiting(user.getVisiting());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User deleteUser = userRepository.getById(id);
        if(deleteUser == null){
            return false;
        }
        userRepository.delete(deleteUser);
        return true;
    }



    public String buyProduct(Integer idu, Integer idp, Integer idm){
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if(userRepository.findAll().get(i).getId().equals(idu)){
                for (int j = 0; j < productRepository.findAll().size(); j++) {
                   if(productRepository.findAll().get(j).getId().equals(idp)){
                       for (int k = 0; k < merchantRepository.findAll().size(); k++) {
                           if(merchantRepository.findAll().get(k).getId().equals(idm)){
                              for (int b=0;b<merchantStockRepository.findAll().size();b++) {
                                  if(merchantStockRepository.findAll().get(b).getMerchant_id().equals(idm)){
                                      if(merchantStockRepository.findAll().get(b).getStock() >0){
                                          if(userRepository.findAll().get(i).getBalance()>productRepository.findAll().get(j).getPrice()){
                                              merchantStockRepository.findAll().get(b).setStock(merchantStockRepository.findAll().get(b).getStock()-1);
                                              userRepository.findAll().get(i).setBalance(userRepository.findAll().get(i).getBalance()-productRepository.findAll().get(j).getPrice());
                                              userRepository.findAll().get(i).setItem(userRepository.findAll().get(i).getItem()+1);
                                              userRepository.findAll().get(i).setVisiting(userRepository.findAll().get(i).getVisiting()+1);
                                              return "Done";
                                          }
                                          return "Balance less than price";
                                      }
                                      return "Not have enough stock";
                                  }

                              }
                               return "merchant id not equal with merchant stock id";
                           }

                       }
                       return "merchant id not found";
                   }

                }
                return "product id not found";
            }

        }
        return "user id not found";
    }

    // extra-1 (delivery Order)
    public Boolean deliverProduct(Integer id){
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if(userRepository.findAll().get(i).getId().equals(id) && userRepository.findAll().get(i).getItem()>0){
                userRepository.findAll().get(i).setItem(0);
                        return true;
            }
        }
        return false;
    }

    // extra-2 (subscribe)
    public Boolean giveSubscribe(Integer ida,Integer idc){
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if(userRepository.findAll().get(i).getId().equals(ida) && userRepository.findAll().get(i).getRole().equals("Admin")){
                for (int j = 0; j < userRepository.findAll().size(); j++) {
                    if(userRepository.findAll().get(j).getId().equals(idc) && userRepository.findAll().get(j).getVisiting()>5){
                        userRepository.findAll().get(j).setSubscribe("prime");
                        return true;
                    }
                }

            }
        }
        return false;
    }

   /* // extra-3 (recover product)
    public String recoverProduct(String idu, String idp) {
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (userRepository.findAll().get(i).getId().equals(idu)) {
                for (int j = 0; j < userRepository.findAll().get(i).getOrederList().size(); j++) {
                    if (productService.getProducts().get(j).getId().equals(idp)) {
                        users.get(i).setBalance(users.get(i).getBalance()+productService.getProducts().get(j).getPrice());
                        users.get(i).setItem(users.get(i).getItem()+1);
                        users.get(i).getOrederList().remove(j);
                        return "Done Recovery";
                    }
                }

            }

        }
        return "user id not found";
    }*/



    // extra-4 (write comment)
    public String commentProduct(String ida,String idp,String comment){

        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if(userRepository.findAll().get(i).getId().equals(ida)){
                for (int j = 0; j < productRepository.findAll().size(); j++) {
                    if(productRepository.findAll().get(j).getId().equals(idp)){
                        productRepository.findAll().get(j).setComments(productRepository.findAll().get(j).getComments()+("("+comment+")"));
                        return "Done comment";
                    }
                }
                return "product id not found";
            }
        }
        return "user id not found";
    }

    // extra-5 (prime feature)
    public String PrimebuyProduct(String idu, String idp, String idm){
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if(userRepository.findAll().get(i).getId().equals(idu)){
                for (int j = 0; j < productRepository.findAll().size(); j++) {
                    if(productRepository.findAll().get(j).getId().equals(idp)){
                        for (int k = 0; k < merchantRepository.findAll().size(); k++) {
                            if(merchantRepository.findAll().get(k).getId().equals(idm)){
                                for (int b=0;b<merchantStockRepository.findAll().size();b++) {
                                    if(merchantStockRepository.findAll().get(b).getMerchant_id().equals(idm)){
                                        if(merchantStockRepository.findAll().get(b).getStock() >0){
                                            if(userRepository.findAll().get(i).getBalance()>productRepository.findAll().get(j).getPrice()){
                                               if(userRepository.findAll().get(i).getSubscribe().equals("Prime") && userRepository.findAll().get(i).getVisiting()>5 && userRepository.findAll().get(i).getVisiting()<10){
                                                   merchantStockRepository.findAll().get(b).setStock(merchantStockRepository.findAll().get(b).getStock()-1);
                                                   userRepository.findAll().get(i).setBalance(userRepository.findAll().get(i).getBalance()-((productRepository.findAll().get(j).getPrice())-(productRepository.findAll().get(j).getPrice()*0.10)));
                                                   userRepository.findAll().get(i).setItem(userRepository.findAll().get(i).getItem()+1);
                                                   userRepository.findAll().get(i).setVisiting(userRepository.findAll().get(i).getVisiting()+1);
                                                   return "Done";
                                               } else if(userRepository.findAll().get(i).getSubscribe().equals("Prime") && userRepository.findAll().get(i).getVisiting()>10 && userRepository.findAll().get(i).getVisiting()<15){
                                                   merchantStockRepository.findAll().get(b).setStock(merchantStockRepository.findAll().get(b).getStock()-1);
                                                   userRepository.findAll().get(i).setBalance(userRepository.findAll().get(i).getBalance()-((productRepository.findAll().get(j).getPrice())-(productRepository.findAll().get(j).getPrice()*0.20)));
                                                   userRepository.findAll().get(i).setItem(userRepository.findAll().get(i).getItem()+1);
                                                   userRepository.findAll().get(i).setVisiting(userRepository.findAll().get(i).getVisiting()+1);
                                                   return "Done";
                                               } else if(userRepository.findAll().get(i).getSubscribe().equals("Prime") && userRepository.findAll().get(i).getVisiting()>=15){
                                                   merchantStockRepository.findAll().get(b).setStock(merchantStockRepository.findAll().get(b).getStock()-1);
                                                   userRepository.findAll().get(i).setBalance(userRepository.findAll().get(i).getBalance()-((productRepository.findAll().get(j).getPrice())-(productRepository.findAll().get(j).getPrice()*0.30)));
                                                   userRepository.findAll().get(i).setItem(userRepository.findAll().get(i).getItem()+1);
                                                   userRepository.findAll().get(i).setVisiting(userRepository.findAll().get(i).getVisiting()+1);
                                                   return "Done";
                                               }

                                            }
                                            return "Balance less than price";
                                        }
                                        return "Not have enough stock";
                                    }

                                }
                                return "merchant id not equal with merchant stock id";
                            }

                        }
                        return "merchant id not found";
                    }

                }
                return "product id not found";
            }

        }
        return "user id not found";
    }



}


