package com.example.ecomertest.controller;

import com.example.ecomertest.entity.*;
import com.example.ecomertest.entity.CompositeKey.CartItemKey;
import com.example.ecomertest.repository.CartItemRepo;
import com.example.ecomertest.repository.CartRepo;
import com.example.ecomertest.repository.ProductRepo;
import com.example.ecomertest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/add")
    public String addCart(@RequestParam(name="id") Long id, Model model){
        UserAcoount userAcoount = (UserAcoount) SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        User user = userRepo.findById(userAcoount.getUser().getId()).get();
        Cart cart = cartRepo.findByUserAndState(user, true);
        Product product = productRepo.findById(id).get();
        if(cart == null){
            cart = new Cart();
            cart.setUser(user);
            cartRepo.save(cart);
        }
        CartItemKey cartItemKey = new CartItemKey();
        cartItemKey.setCartId(cart.getId());
        cartItemKey.setProductId(product.getId());
        CartItem cartItem = cartItemRepo.findByCartItemKey(cartItemKey);
        if(cartItem == null){
            cartItem = new CartItem();
            cartItem.setCartItemKey(cartItemKey);
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuanity(1);
            cartItem.setTotalprice(cartItem.getQuanity()*product.getPrice());
        } else {
            cartItem.setQuanity(cartItem.getQuanity()+1);
            cartItem.setTotalprice(cartItem.getQuanity()*product.getPrice());
        }
        cartItemRepo.save(cartItem);
        cartRepo.save(cart);
        Set<CartItem> cartItems = new HashSet<>(cartItemRepo.findByCart(cart));
        cart.setCartItems(cartItems);
        cart.setTotal(totalPrice(cartItems));
        cartRepo.save(cart);
        model.addAttribute("cart",cart);
        return "cart/cart_detail";
    }
    private Integer totalPrice(Set<CartItem> cartItems){

        int tong = 0;
        Iterator<CartItem> iter = cartItems.iterator();
        while(iter.hasNext()){
            CartItem cartItem = iter.next();
            cartItem.setTotalprice(cartItem.getQuanity()*cartItem.getProduct().getPrice());
            tong = tong + cartItem.getQuanity()*cartItem.getProduct().getPrice();
        }
        return tong;
    }
    @GetMapping("/{idcard}/{idproduct}/delete")
    public String deleteCartItem(@PathVariable(name="idcard") Long idcart, @PathVariable(name="idproduct") Long idproduct, Model model ){
        CartItemKey cartItemKey = new CartItemKey();
        cartItemKey.setCartId(idcart);
        cartItemKey.setProductId(idproduct);
        CartItem cartItem = cartItemRepo.findByCartItemKey(cartItemKey);
        cartItemRepo.delete(cartItem);
        Cart cart = cartRepo.findById(idcart).get();
        cart.setTotal(totalPrice(cart.getCartItems()));
        cartRepo.save(cart);
        model.addAttribute("cart",cart);
        return "cart/cart_detail";
    }
    @GetMapping
    public String renderCart(Model model){
        UserAcoount userAcoount = (UserAcoount) SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        User user = userRepo.findById(userAcoount.getUser().getId()).get();
        Cart cart = cartRepo.findByUserAndState(user,true);
        if(cart == null){
            cart = new Cart();
            model.addAttribute("cart",cart);
            return "cart/cart_detail";
        }
        cart.setTotal(totalPrice(cart.getCartItems()));
        model.addAttribute("cart",cart);
        return "cart/cart_detail";
    }
    @GetMapping("/{idcard}/{idproduct}/update")
    public String updateCartItem(@PathVariable(name="idcard") Long idcart, @PathVariable(name="idproduct") Long idproduct,@RequestParam(name="quantity") Integer quantity,Model model){
        if(quantity == 0){
            return "redirect:/cart/"+idcart+"/"+idproduct+"/delete";
        }else {
            CartItemKey cartItemKey = new CartItemKey();
            cartItemKey.setCartId(idcart);
            cartItemKey.setProductId(idproduct);
            CartItem cartItem = cartItemRepo.findByCartItemKey(cartItemKey);
            cartItem.setQuanity(quantity);
            Cart cart = cartRepo.findById(idcart).get();
            cart.setTotal(totalPrice(cart.getCartItems()));
            cartRepo.save(cart);
            model.addAttribute("cart",cart);
            return "cart/cart_detail";
        }
    }
}
