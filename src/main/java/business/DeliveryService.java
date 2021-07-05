package business;

import data.FileWriter;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static business.BaseProduct.distinctByKey;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    public HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    public List<MenuItem> products = new ArrayList<>();
    public ArrayList<MenuItem> items = new ArrayList<>();
    public ArrayList<Order> orderList = new ArrayList<>();
    public static int orderCounter = 1;
    public static int userCounter = 1;
    public List<User> users = new ArrayList<>();

    public DeliveryService() {
        BaseProduct b = new BaseProduct();
        List<BaseProduct> prod = b.loadItemsFromCsvFile();
        for (BaseProduct i : prod) {
            products.add(i);
        }
        assert isWellFormed();
    }

    public boolean isWellFormed(){
        assert !products.contains(null);
        return !products.contains(null);
    }

    @Override
    public List<BaseProduct> importProducts() {
        List<BaseProduct> menu = new ArrayList<>();
        BaseProduct b = new BaseProduct();
        menu = b.loadItemsFromCsvFile();

        return menu;
    }

    /**
     * Method used to create a new BaseProduct
     *
     * @param menuItem
     * @throws AssertionError
     */
    @Override
    public void addProduct(BaseProduct menuItem) {
        assert menuItem != null;
        int size = products.size();

        products.add(menuItem);

        assert size + 1 == products.size();
    }

    /**
     * Method used to delete a given product
     *
     * @param menuItemName
     * @return
     */
    @Override
    public boolean deleteProduct(String menuItemName) {
        assert menuItemName != null;
        int size = products.size();

        for (MenuItem i : products) {
            if (menuItemName.equals(i.getTitle())) {
                products.remove(i);
                assert size - 1 == products.size();
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to modify the price of a product
     *
     * @param menuItemName
     * @param price
     * @return
     */
    @Override
    public boolean updateProduct(String menuItemName, int price) {
        assert menuItemName != null;
        int p = 0;

        for (MenuItem i : products) {
            if (i instanceof BaseProduct) {
                if (menuItemName.equals(i.getTitle())) {
                    i.setPrice(price);
                    p = i.getPrice();
                    assert p == i.getPrice();
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Composite products cannot be edited.", null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return false;
    }

    /**
     * Method used to create a CompositeProduct
     *
     * @param title
     * @param prod1
     * @param prod2
     * @param prod3
     * @throws AssertionError
     */
    @Override
    public void createCompositeProduct(String title, String prod1, String prod2, String prod3) {
        assert title != null;
        int size = products.size();

        BaseProduct p1 = new BaseProduct();
        BaseProduct p2 = new BaseProduct();
        BaseProduct p3 = new BaseProduct();
        for (MenuItem i : products) {
            if (prod1.equals(i.getTitle())) {
                p1 = (BaseProduct) i;
            }
            if (prod2.equals(i.getTitle())) {
                p2 = (BaseProduct) i;
            }
            if (prod3.equals(i.getTitle())) {
                p3 = (BaseProduct) i;
            }
        }
        CompositeProduct c = new CompositeProduct();
        List<MenuItem> items = new ArrayList<>();
        items.add(p1);
        items.add(p2);
        items.add(p3);
        c.setItems(items);
        c.setTitle(title);
        c.computePrice();
        c.compute();
        products.add(c);

        assert size + 1 == products.size();
    }

    /**
     * Method used to register a new client into the system
     *
     * @param username
     * @param password
     */
    @Override
    public void registerUser(String username, String password) {
        users.add(new User(userCounter++, username, password));
    }

    public boolean login(User user) {
        for (User i : users) {
            if (i.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<MenuItem> addItem(ArrayList<MenuItem> menuItems) {
        ArrayList<MenuItem> ord = new ArrayList<>();
        for (MenuItem i : menuItems) {
            ord.add(i);
        }
        return ord;
    }

    public void addOrderItem(String item, int userId) {
        for (MenuItem i : products) {
            if (i.getTitle().equals(item)) {
                items.add(i);
                i.orderedTimes++;
                break;
            }
        }
        for (User u : users) {
            if (u.getId() == userId) {
                u.nrOrders++;
            }
        }
    }

    public void clearItems() {
        items.clear();
    }

    /**
     * Method used by client to create an order
     *
     * @param userID
     * @param items
     * @throws FileNotFoundException
     */
    @Override
    public void createOrder(int userID, ArrayList<MenuItem> items) throws FileNotFoundException{
        assert userID != 0;
        assert items != null;

        Order order = new Order(orderCounter++, userID);
        orders.put(order, addItem(items));

        setChanged();
        notifyObservers(order);

        for (User u : users) {
            if (u.getId() == userID)
                u.nrOrders++;
        }
        generateBill(order.getOrderID());

        assert order.equals(order);
    }

    /**
     * Method used to compute the total price of the order
     *
     * @return
     */
    @Override
    public int computeOrderPrice() {
        int price = 0;
        for (MenuItem m : items) {
            price += m.computePrice();
        }

        assert price >= 0;
        return price;
    }

    /**
     * Method used to create a bill with an order
     *
     * @param orderID
     */
    @Override
    public void generateBill(int orderID) {
        assert orderID != 0;

        new FileWriter(orderID, orders, computeOrderPrice());
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<MenuItem> searchByTitle(String title) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getTitle().equals(title)).collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByRating(float rating) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getRating() == rating).collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByPrice(int price) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getPrice() == price).collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByTitleAndPrice(String title, int price) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getTitle().equals(title) && p.getPrice() == price)
                .collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByTitleAndRaiting(String title, float rating) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getTitle().equals(title) && p.getRating() == rating)
                .collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByTitleRatingPrice(String title, float rating, int price) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream()
                .filter(p -> p.getTitle().equals(title) && p.getPrice() == price && p.getRating() == rating)
                .collect(Collectors.toList());
        return prod;
    }

    @Override
    public List<MenuItem> searchByRatingPrice(float rating, int price) {
        List<MenuItem> prod = new ArrayList<>();
        prod = products.stream().filter(p -> p.getPrice() == price && p.getRating() == rating)
                .collect(Collectors.toList());
        return prod;
    }

    @Override
    public void generateTimeReport(LocalDateTime start, LocalDateTime finish) throws FileNotFoundException {
        File f = new File("Report1.txt");
        PrintWriter printWriter = new PrintWriter(f);
        List<Order> ord = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        printWriter.println("Orders performed between " + start.format(formatter) + "  " + finish.format(formatter));

        for (Map.Entry<Order, ArrayList<MenuItem>> i : orders.entrySet()) {
            ord.add(i.getKey());
        }
        ord = ord.stream().filter(o -> o.getOrderDate().isAfter(start)).filter(o -> o.getOrderDate().isBefore(finish))
                .collect(Collectors.toList());
        for (Order i : ord) {
            printWriter.print("ID: " + i.getOrderID() + "  ");
            printWriter.print("Date: " + i.getOrderDate().format(formatter) + "  ");
            printWriter.println("Client: " + i.getClientID() + "  ");
        }
        printWriter.close();
    }

    @Override
    public void generateProductsReport(int nr) throws FileNotFoundException {
        File f = new File("Report2.txt");
        PrintWriter printWriter = new PrintWriter(f);
        printWriter.println("The products ordered more than " + nr + " number of times");

        products = products.stream().filter(p -> p.orderedTimes > nr).collect(Collectors.toList());
        for (MenuItem i : products) {
            printWriter.println("Product: " + i.getTitle() + ", ordered " + i.orderedTimes + " times");
        }
        printWriter.close();
    }

    @Override
    public void generateClientsReport(int nr, int value) throws FileNotFoundException {
        File f = new File("Report3.txt");
        PrintWriter printWriter = new PrintWriter(f);
        printWriter.println("The clients that have ordered more than " + nr
                + " times and the value of the order was higher than " + value);

        List<Order> ord = new ArrayList<>();
        for (Map.Entry<Order, ArrayList<MenuItem>> i : orders.entrySet()) {
            ord.add(i.getKey());
        }
        List<User> u = new ArrayList<>();
        u = u.stream().filter(n -> n.nrOrders > nr).collect(Collectors.toList());
        for (User i : u) {
            ord = ord.stream().filter(n -> n.clientID == i.getId()).collect(Collectors.toList());
        }
        ord = ord.stream().filter(distinctByKey(n -> n.clientID)).collect(Collectors.toList());
        ord.forEach(o -> printWriter.println("CLient: " + o.clientID));
        printWriter.close();
    }

    @Override
    public void generateProductsDayReport(LocalDate date) throws FileNotFoundException {
        File f = new File("Report4.txt");
        PrintWriter printWriter = new PrintWriter(f);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        printWriter.println("The products ordered within " + date.format(formatter)
                + " and the number of times they have been ordered");
        List<Order> ord = new ArrayList<>();

        for (Map.Entry<Order, ArrayList<MenuItem>> i : orders.entrySet()) {
            ord.add(i.getKey());
        }
        ord = ord.stream().filter(o -> o.getOrderDate().toLocalDate().equals(date)).collect(Collectors.toList());

        List<MenuItem> prod = new ArrayList<>();

        for (Order i : ord) {
            for (Map.Entry<Order, ArrayList<MenuItem>> j : orders.entrySet()) {
                if (j.getKey().equals(i)) {
                    for (MenuItem m : j.getValue()) {
                        prod.add(m);
                    }
                }
            }
        }

        prod = prod.stream().filter(distinctByKey(p -> p.getTitle())).collect(Collectors.toList());
        for (MenuItem item : prod) {
            printWriter.print("Product: " + item.getTitle() + "  ");
            printWriter.println("Nr. of times ordered: " + item.orderedTimes + "  ");
        }
        printWriter.close();
    }
}
