package com.example.ecomertest.controller;

import com.example.ecomertest.entity.*;
import com.example.ecomertest.repository.CartRepo;
import com.example.ecomertest.repository.OrderRepo;
import com.example.ecomertest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderRepo orderRepo;
    @GetMapping("/check")
    public String renderOrder(Model model, @RequestParam(name="cartid") Long cartid){
        Cart cart = cartRepo.findById(cartid).get();
        totalPrice(cart.getCartItems());
        model.addAttribute("cart",cart);
        return "order/order_check";
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
    @PostMapping("/ordersuccess")
    public String orderSuccess(@RequestParam(name="cartid") Long cartid, @RequestParam(name="shipment") Integer shipment, @RequestParam(name="payment") Integer payment){
        Cart cart = cartRepo.findById(cartid).get();
        cart.setState(false);
        Order order = new Order();
        order.setCart(cart);
        Shipment s = new Shipment();
        order.setShipment(s);
        if(shipment == 0){
          s.setName("Giao hàng tiêu chuẩn");
          s.setPrice(3);
        }else if(shipment == 1){
            s.setName("Giao hàng tiết kiệm");
            s.setPrice(2);
        } else{
            s.setName("Giao hàng nhanh");
            s.setPrice(4);
        }
        Payment p = new Payment();
        order.setPayment(p);
        if(payment == 0){
            p.setName("Thanh toán khi nhận hàng");
        }else{
            p.setName("Thanh toán bằng paypal");
        }
        s.setOrder(order);
        p.setOrder(order);
        cartRepo.save(cart);
        orderRepo.save(order);
        return "order/order_success";
    }
    @GetMapping("/renderlistorder")
    public String renderOrderList(Model model){
        UserAcoount userAcoount = (UserAcoount) SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        User user = userRepo.findById(userAcoount.getUser().getId()).get();
        List<Order> orders = orderRepo.findOrderByCart_User_Id(user.getId());
        model.addAttribute("orders",orders);
        return "order/order_list";
    }
}
