package cosmetic.vo;

public class Product {
    String productNo;
    String name;
    int price;

    public Product(){

    }

    public Product(String productNo, String name, int price) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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
}
