package com.techelevator.dao;

import com.techelevator.model.BusinessHours;
import com.techelevator.model.Cake;
import com.techelevator.model.CakeOption;
import com.techelevator.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {

    List<Cake> getStandardCakes();

    int savePremadeOrder(Order order);

    int saveCake(Cake cake);

    List<Order> getPendingOrders();

    void completeOrder(int id);

    void readyOrder(int id);

    void cancelledOrder(int id);

    void makeCakeUnavailable(int id);

    void makeCakeAvailable(int id);

    List<CakeOption> getCakeOptions();

    void deleteCakeOption(int id);

   boolean isWithinBusinessHours (String pickupDate);

    List<BusinessHours> getBusinessHours();

    void saveCakeOption(CakeOption option);

    List<CakeOption> getCakeFlavors();

    List<CakeOption> getCakeIcings();

    List<CakeOption> getCakeFillings();

    List<CakeOption> getCakeSizes();

    List<CakeOption> getCakeShapes();

    List<Order> getHistoricalReport(String startDate, String endDate);

    List<Order> upcomingCalendar();

    List<Order> searchByOrderId(String input);

    List<Order> searchByCustomerName(String input);

    List<Order> searchByCustomerEmail(String input);

    List<Order> searchByCustomerPhone(String input);
}
