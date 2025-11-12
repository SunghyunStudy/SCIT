package vo;

public class Product {
    private String productNo;
    private String name;
    private int price;

    public Product() {
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

    @Override
    public String toString() {
        String tmp = String.format("상품 번호=%s, 상품명=%s, 가격=%,d원, ", productNo, name, price); // 사용방법은 printf와 동일하다.
        return tmp;
    }
}
