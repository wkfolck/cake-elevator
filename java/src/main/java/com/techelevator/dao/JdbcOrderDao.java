package com.techelevator.dao;

import com.techelevator.model.BusinessHours;
import com.techelevator.model.Cake;
import com.techelevator.model.CakeOption;
import com.techelevator.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JdbcOrderDao implements OrderDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Cake> getStandardCakes() {
        List<Cake> cakes = new ArrayList<>();
        String sql ="SELECT cake_id, flavor, icing, size, num_of_tiers, shape, filling, is_standard, image_url, price, available FROM cakes WHERE is_standard = 'true' ORDER BY cake_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Cake cake =  mapRowToCake(results);
            cakes.add(cake);
        }
        return cakes;
    }

    @Override
    public int savePremadeOrder(Order order) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String sql = "INSERT INTO orders(cake_id, message, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, price, status) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING order_id";
        int id = jdbcTemplate.queryForObject(sql, int.class, order.getCake_id(), order.getMessage(), order.getCustomer_name(), order.getCustomer_address(), order.getCustomer_city(), order.getCustomer_state(), order.getCustomer_zip(), order.getCustomer_phone(), order.getCustomer_email(), localDateTime, order.getPickup_date(), order.getPrice(), order.getStatus());
        return id;
    }

    @Override
    public int saveCake(Cake cake) {
        String sql = "INSERT INTO cakes(flavor, icing, size, num_of_tiers, shape, filling, is_standard, image_url, price, available) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING cake_id";
        int id = jdbcTemplate.queryForObject(sql, int.class, cake.getFlavor(), cake.getIcing(), cake.getSize(), cake.getNumOfTiers(), cake.getShape(), cake.getFilling(), cake.isStandard(), cake.getImageURL(), cake.getPrice(), cake.isAvailable());
        return id;
    }

    @Override
    public List<Order> getPendingOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE status = 'pending';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Order order = mapRowToPendingOrder(results);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public void completeOrder(int id) {
        String sql = "UPDATE orders SET status = 'Completed', completed_date = ? WHERE order_id = ?;";
        LocalDate todaysDate = LocalDate.now();
        jdbcTemplate.update(sql, todaysDate, id);
    }

    @Override
    public void readyOrder(int id) {
        String sql = "UPDATE orders SET status = 'Ready' WHERE order_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void cancelledOrder(int id) {
        String sql = "UPDATE orders SET status = 'Cancelled' WHERE order_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void makeCakeAvailable(int id) {
        String sql = "UPDATE cakes SET available = true WHERE cake_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void makeCakeUnavailable(int id) {
        String sql = "UPDATE cakes SET available = false WHERE cake_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<CakeOption> getCakeOptions() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options ORDER BY option_type";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public void deleteCakeOption(int id) {
        String sql = "DELETE from cake_options WHERE option_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void saveCakeOption(CakeOption option) {
        String sql = "INSERT INTO cake_options(option_name, option_type, additional_price) " +
                "VALUES(?, ?, ?);";
        jdbcTemplate.update(sql, option.getOptionName(), option.getOptionType(), option.getAdditionalPrice());
    }

    @Override
    public List<CakeOption> getCakeFlavors() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options WHERE option_type = 'Flavor'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public List<CakeOption> getCakeIcings() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options WHERE option_type = 'Icing'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public List<CakeOption> getCakeFillings() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options WHERE option_type = 'Filling'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public List<CakeOption> getCakeSizes() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options WHERE option_type = 'Size'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public List<CakeOption> getCakeShapes() {
        List<CakeOption> options = new ArrayList<>();
        String sql ="SELECT option_id, option_name, option_type, additional_price FROM cake_options WHERE option_type = 'Shape'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CakeOption option =  mapRowToOptions(results);
            options.add(option);
        }
        return options;
    }

    @Override
    public List<BusinessHours> getBusinessHours() {
        List<BusinessHours> hours = new ArrayList<>();
        String sql ="SELECT weekday_id, weekday_name, open_time, close_time " +
                "FROM business_hours";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            BusinessHours hour =  mapRowToBusinessHours(results);
            hours.add(hour);
        }
        return hours;
    }

    @Override
    public List<Order> upcomingCalendar() {
        Timestamp end = Timestamp.valueOf(LocalDateTime.now().plusDays(8));
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE status = 'Ready' AND pickup_date <= ? OR status = 'pending' AND pickup_date <= ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, end, end);
        while(results.next()) {
            Order order = mapRowToPendingOrder(results);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean isWithinBusinessHours (String pickupDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String sub1 = pickupDate.substring(0, pickupDate.length()-2);
        String sub2 = pickupDate.substring(pickupDate.length()-2).toUpperCase();
        pickupDate = sub1 + sub2;
       LocalDateTime convertedPickupDate = LocalDateTime.parse(pickupDate, formatter);
        List<BusinessHours> hours = getBusinessHours();
        LocalTime convertedPickupTime = convertedPickupDate.toLocalTime();
        String dayOfTheWeek = convertedPickupDate.getDayOfWeek().toString().toLowerCase();
        boolean isValidPickUpTime = false;
        String sunday = hours.get(0).getWeekday_name().toLowerCase();
        String monday = hours.get(1).getWeekday_name().toLowerCase();
        String tuesday = hours.get(2).getWeekday_name().toLowerCase();
        String wednesday = hours.get(3).getWeekday_name().toLowerCase();
        String thursday = hours.get(4).getWeekday_name().toLowerCase();
        String friday = hours.get(5).getWeekday_name().toLowerCase();
        String saturday = hours.get(6).getWeekday_name().toLowerCase();

        if(dayOfTheWeek.equals(sunday)){
            isValidPickUpTime = false;
            return isValidPickUpTime;
        } else if(dayOfTheWeek.equals(monday) || dayOfTheWeek.equals(tuesday) ||
                dayOfTheWeek.equals(wednesday) || dayOfTheWeek.equals(thursday)){
            if(convertedPickupTime.isAfter(hours.get(1).getOpen_time()) && convertedPickupTime.isBefore(hours.get(1).getClose_time())){
                isValidPickUpTime = true;
                return isValidPickUpTime;
            }
            return isValidPickUpTime;
        } else if(dayOfTheWeek.equals(friday) || dayOfTheWeek.equals(saturday)) {
            if(convertedPickupTime.isAfter(hours.get(5).getOpen_time()) && convertedPickupTime.isBefore(hours.get(5).getClose_time())) {
                isValidPickUpTime = true;
                return isValidPickUpTime;
            }
            return isValidPickUpTime;
        }
        return isValidPickUpTime;
    }

    @Override
    public List<Order> getHistoricalReport(String startDate, String endDate) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status, completed_date " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE status = 'Completed' AND completed_date >= ? AND completed_date <= ?;";
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, start, end);
        while(results.next()) {
            Order order = mapRowToOrder(results);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Order> searchByOrderId(String input){
        int newInput = Integer.parseInt(input);
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE order_id = ? AND status <> 'Completed';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newInput);
        while(results.next()) {
            Order order = mapRowToPendingOrder(results);
            orders.add(order);
        }
        return orders;
    }

    public List<Order> searchByCustomerName(String input) {
        input = "%" + input + "%";
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
            "FROM orders " +
            "JOIN cakes ON orders.cake_id = cakes.cake_id " +
            "WHERE customer_name ILIKE ? AND status <> 'Completed';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, input);
        while(results.next()) {
        Order order = mapRowToPendingOrder(results);
        orders.add(order);
    }
        return orders;
}


    public List<Order> searchByCustomerEmail(String input) {
        input = "%" + input + "%";
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE customer_email ILIKE ? AND status <> 'Completed';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, input);
        while(results.next()) {
            Order order = mapRowToPendingOrder(results);
            orders.add(order);
        }
        return orders;
    }

    public List<Order> searchByCustomerPhone(String input) {
        input = "%" + input + "%";
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT orders.order_id, customer_name, customer_address, customer_city, customer_state, customer_zip, customer_phone, customer_email, order_date, pickup_date, orders.cake_id, flavor, icing, cakes.size, num_of_tiers, shape, filling, is_standard, orders.price, orders.status  " +
                "FROM orders " +
                "JOIN cakes ON orders.cake_id = cakes.cake_id " +
                "WHERE customer_phone ILIKE ? AND status <> 'Completed';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, input);
        while(results.next()) {
            Order order = mapRowToPendingOrder(results);
            orders.add(order);
        }
        return orders;
    }

    private Cake mapRowToCake(SqlRowSet rs) {
        Cake cake = new Cake();
        cake.setCakeId(rs.getLong("cake_id"));
        cake.setFlavor(rs.getString("flavor"));
        cake.setIcing(rs.getString("icing"));
        cake.setSize(rs.getString("size"));
        cake.setNumOfTiers(rs.getInt("num_of_tiers"));
        cake.setShape(rs.getString("shape"));
        cake.setFilling(rs.getString("filling"));
        cake.setStandard(rs.getBoolean("is_standard"));
        cake.setImageURL(rs.getString("image_url"));
        cake.setPrice(rs.getBigDecimal("price"));
        cake.setAvailable(rs.getBoolean("available"));
        return cake;

    }

    private Order mapRowToOrder(SqlRowSet rs) {
        Order order = new Order();
        order.setOrder_id(rs.getInt("order_id"));
        order.setStatus(rs.getString("status"));
        order.setCustomer_name(rs.getString("customer_name"));
        order.setCustomer_address(rs.getString("customer_address"));
        order.setCustomer_city(rs.getString("customer_city"));
        order.setCustomer_state(rs.getString("customer_state"));
        order.setCustomer_zip(rs.getInt("customer_zip"));
        order.setCustomer_phone(rs.getString("customer_phone"));
        order.setCustomer_email(rs.getString("customer_email"));
        order.setOrder_date(rs.getTimestamp("order_date").toLocalDateTime());
        order.setPickup_date(rs.getTimestamp("pickup_date").toLocalDateTime());
        order.setCake_id(rs.getInt("cake_id"));
        order.setFlavor(rs.getString("flavor"));
        order.setIcing(rs.getString("icing"));
        order.setSize(rs.getString("size"));
        order.setNum_Of_Tiers(rs.getInt("num_of_tiers"));
        order.setShape(rs.getString("shape"));
        order.setFilling(rs.getString("filling"));
        order.setIs_Standard(rs.getBoolean("is_standard"));
        order.setPrice(rs.getBigDecimal("price"));
        order.setCompleted_date(rs.getDate("completed_date").toLocalDate());
        return order;
    }

    private Order mapRowToPendingOrder(SqlRowSet rs) {
        Order order = new Order();
        order.setOrder_id(rs.getInt("order_id"));
        order.setStatus(rs.getString("status"));
        order.setCustomer_name(rs.getString("customer_name"));
        order.setCustomer_address(rs.getString("customer_address"));
        order.setCustomer_city(rs.getString("customer_city"));
        order.setCustomer_state(rs.getString("customer_state"));
        order.setCustomer_zip(rs.getInt("customer_zip"));
        order.setCustomer_phone(rs.getString("customer_phone"));
        order.setCustomer_email(rs.getString("customer_email"));
        order.setOrder_date(rs.getTimestamp("order_date").toLocalDateTime());
        order.setPickup_date(rs.getTimestamp("pickup_date").toLocalDateTime());
        order.setCake_id(rs.getInt("cake_id"));
        order.setFlavor(rs.getString("flavor"));
        order.setIcing(rs.getString("icing"));
        order.setSize(rs.getString("size"));
        order.setNum_Of_Tiers(rs.getInt("num_of_tiers"));
        order.setShape(rs.getString("shape"));
        order.setFilling(rs.getString("filling"));
        order.setIs_Standard(rs.getBoolean("is_standard"));
        order.setPrice(rs.getBigDecimal("price"));
        return order;
    }

    private CakeOption mapRowToOptions(SqlRowSet rs) {
        CakeOption option = new CakeOption();
        option.setOptionId(rs.getInt("option_id"));
        option.setOptionName(rs.getString("option_name"));
        option.setOptionType(rs.getString("option_type"));
        option.setAdditionalPrice(rs.getBigDecimal("additional_price"));
        return option;

    }

    private BusinessHours mapRowToBusinessHours(SqlRowSet rs) {
        BusinessHours hours = new BusinessHours();
        hours.setWeekday_id(rs.getInt("weekday_id"));
        hours.setWeekday_name(rs.getString("weekday_name"));
        hours.setOpen_time(rs.getTime("open_time").toLocalTime());
        hours.setClose_time(rs.getTime("close_time").toLocalTime());
        return hours;

    }


}
