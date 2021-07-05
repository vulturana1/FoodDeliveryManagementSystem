package business;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;

public class BaseProduct extends MenuItem implements Serializable {

    //public static int orderedTimes;

    public BaseProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        orderedTimes = 0;
    }

    public BaseProduct() {
        super();
    }

    public int computePrice() {
        return getPrice();
    }

    public List<BaseProduct> loadItemsFromCsvFile() {
        Pattern pattern = Pattern.compile(",");
        List<BaseProduct> baseProducts = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of("products.csv"))) {
            List<BaseProduct> products = lines.skip(1).map(line -> {
                String[] arr = pattern.split(line);
                baseProducts.add(new BaseProduct(arr[0], Float.parseFloat(arr[1]), Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]),
                        Integer.parseInt(arr[6])));
                return new BaseProduct(arr[0], Float.parseFloat(arr[1]), Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]),
                        Integer.parseInt(arr[6]));
            }).collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("The file does not exist.");
        }

        // remove duplicates
        List<BaseProduct> products = baseProducts.stream().filter(distinctByKey(p -> p.getTitle()))
                .collect(Collectors.toList());
        return products;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}