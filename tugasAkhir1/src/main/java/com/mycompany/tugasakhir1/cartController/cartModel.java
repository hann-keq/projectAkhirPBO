/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.cartController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hanif
 */
public class cartModel {
    private ObservableList<Cart> selectedItem = FXCollections.observableArrayList();
    
    private static class HelperClass{
        private static final cartModel INSTANCE = new cartModel();
    }
    
    public static cartModel getInstance(){
        return HelperClass.INSTANCE;
    }
    
     public ObservableList<Cart> getSelectedItem(){
        return selectedItem;
    }
     public  void duplicateEraser (Cart cart){
         for (Cart item : selectedItem){
             if(item.getIdMakanan() == cart.getIdMakanan()){
                 System.out.println("item"+item.getIdMakanan()+"==?"+cart.getIdMakanan());
                
                 
                item.setJumlah(item.getJumlah()+1);
                    return;
             }
            }
         cart.setJumlah(1);
         selectedItem.add(cart);
        }
}
