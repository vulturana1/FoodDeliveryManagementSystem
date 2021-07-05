package business;

import java.io.Serializable;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {

    private List<MenuItem> items;

    public CompositeProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        orderedTimes = 0;
    }

    public CompositeProduct() {
        super();
    }

    @Override
    public int computePrice() {
        int price = 0;
        for (MenuItem i : items) {
            price = price + i.getPrice();
        }
        this.setPrice(price);

        return price;
    }

    public void compute() {
        int calories = 0;
        float rating = 0;
        int proteins = 0;
        int fats = 0;
        int sodium = 0;
        for (MenuItem i : items) {
            calories += i.getCalories();
            rating += i.getRating();
            proteins += i.getProteins();
            fats += i.getFats();
            sodium += i.getSodium();
        }
        this.setCalories(calories);
        this.setProteins(proteins);
        this.setFats(fats);
        this.setRating(rating);
        this.setSodium(sodium);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
