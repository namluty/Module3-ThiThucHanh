package module3.model;

import java.util.List;
import java.util.Locale;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private String color;
    private String description;
    private List<Locale.Category> categories;

    public Product(int id, String name, int price, int quantity, String color, String description, List<Category> categories) {
    }

    public Product(String name, int price, int quantity, String color, String description, List<Locale.Category> categories) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.categories = categories;
    }

    public Product(String name, int price, int quantity, String color, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Locale.Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Locale.Category> categories) {
        this.categories = categories;
    }
}
