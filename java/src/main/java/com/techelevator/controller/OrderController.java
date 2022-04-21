package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.model.Cake;
import com.techelevator.model.CakeOption;
import com.techelevator.model.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    private OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @RequestMapping(value="/standardcakes", method = RequestMethod.GET)
    public List<Cake> getStandardCakes() {
        return orderDao.getStandardCakes();
    }

    @RequestMapping(value="/standardcakes", method = RequestMethod.POST)
    public int addOrder(@RequestBody Order order) {
        return orderDao.savePremadeOrder(order);
    }

    @RequestMapping(value="/customcake", method = RequestMethod.POST)
    public int addCake(@RequestBody Cake cake) {
        return orderDao.saveCake(cake);
    }

    @RequestMapping(value="/pendingorders", method = RequestMethod.GET)
    public List<Order> getPendingOrders() {
        return orderDao.getPendingOrders();
    }

    @RequestMapping(value="/pendingorders/{id}/completed", method = RequestMethod.PUT)
    public void changeStatusComplete(@PathVariable int id) {
        orderDao.completeOrder(id);
    }

    @RequestMapping(value="/pendingorders/{id}/ready", method = RequestMethod.PUT)
    public void changeStatusReady(@PathVariable int id) {
        orderDao.readyOrder(id);
    }

    @RequestMapping(value="/pendingorders/{id}/cancelled", method = RequestMethod.PUT)
    public void changeStatusCancelled(@PathVariable int id) {
        orderDao.cancelledOrder(id);
    }

    @RequestMapping(value="/standardcakes/{id}/available", method = RequestMethod.PUT)
    public void cakeAvailable(@PathVariable int id) {
        orderDao.makeCakeAvailable(id);
    }

    @RequestMapping(value="/standardcakes/{id}/unavailable", method = RequestMethod.PUT)
    public void cakeUnavailable(@PathVariable int id) {
        orderDao.makeCakeUnavailable(id);
    }

    @RequestMapping(value="/cakeoptions", method = RequestMethod.GET)
    public List<CakeOption> getAllOptions() {
        return orderDao.getCakeOptions();
    }

    @RequestMapping(value="/cakeoptions", method = RequestMethod.POST)
    public void addOptions(@RequestBody CakeOption option) {
        orderDao.saveCakeOption(option);
    }

    @RequestMapping(value="/cakeoptions/{id}", method = RequestMethod.DELETE)
    public void deleteOption(@PathVariable int id) {
        orderDao.deleteCakeOption(id);
    }

    @RequestMapping(value="/cakeoptions/flavors", method = RequestMethod.GET)
    public List<CakeOption> getAllFlavors() {
        return orderDao.getCakeFlavors();
    }

    @RequestMapping(value="/cakeoptions/icings", method = RequestMethod.GET)
    public List<CakeOption> getAllIcings() {
        return orderDao.getCakeIcings();
    }

    @RequestMapping(value="/cakeoptions/fillings", method = RequestMethod.GET)
    public List<CakeOption> getAllFillings() {
        return orderDao.getCakeFillings();
    }

    @RequestMapping(value="/cakeoptions/sizes", method = RequestMethod.GET)
    public List<CakeOption> getAllSizes() {
        return orderDao.getCakeSizes();
    }

    @RequestMapping(value="/cakeoptions/shapes", method = RequestMethod.GET)
    public List<CakeOption> getAllShapes() {
        return orderDao.getCakeShapes();
    }

    @RequestMapping(value="/businesshours/{pickupDate}", method = RequestMethod.GET )
    public boolean isWithinBusinessHours (@PathVariable String pickupDate) {
        return orderDao.isWithinBusinessHours(pickupDate);
    }

    @RequestMapping(value="/historicalreport/{startDate}/{endDate}", method = RequestMethod.GET )
    public List<Order> historicalReport (@PathVariable String startDate, @PathVariable String endDate) {
        return orderDao.getHistoricalReport(startDate, endDate);
    }

    @RequestMapping(value="/upcomingorders", method = RequestMethod.GET)
    public List<Order> upcomingOrders() {
        return orderDao.upcomingCalendar();
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<Order> search(@RequestParam(defaultValue = "null") String orderNumber, @RequestParam(defaultValue = "null") String customerName, @RequestParam(defaultValue = "null") String customerEmail, @RequestParam(defaultValue = "null") String customerPhone) {
        List<Order> results = new ArrayList<>();
        if(!orderNumber.equals("null")) {
            return orderDao.searchByOrderId(orderNumber);
        }
        if(!customerName.equals("null")) {
            return orderDao.searchByCustomerName(customerName);
        }
        if(!customerEmail.equals("null")) {
            return orderDao.searchByCustomerEmail(customerEmail);
        }
        if(!customerPhone.equals("null")) {
            return orderDao.searchByCustomerPhone(customerPhone);
        }
        return results;
    }
}
