package service;

/**
 * 프로젝트의 실제 기능을 담당 -
 * 처리할 데이터를 가지고 있다가, UI에서 넘어온 데이터를 처리하거나,
 * 사용자가 보고자하는 데이터를 UI쪽으로 전달.
 *
 */

import vo.Product;

public class CosmeticServiceImpl implements CosmeticService {
    Product[] list = new Product[50];   // 제품이 등록될 배열
    int count = 0;                      // 배열 내에 제품이 몇 개가 등록되었는지 카운트


    @Override
    public boolean insert(Product product) {
        //
        if (count >= list.length) return false;

        list[count++] = product;
        return true;

    }

    @Override
    public boolean update(Product product) {
        String tmp = product.getProductNo();

        for(int i = 0; i < count; i++){
            if(list[i].getProductNo().equals(tmp)){
                list[i] = product;  
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String productNo) {
        for(int i = 0; i < count; ++i){
            if(list[i].getProductNo().equals(productNo)){
                for(int j = i; j <= count; ++j){
                    if(j == count){
                        list[j] = null;
                        count--;
                        return true;
                    }
                    list[j] = list[j+1];
                }
            }
        }
        return false;
    }

    @Override
    public Product selectOne(String productNo) {
        for(int i = 0; i < count; i++){
            if(list[i].getProductNo().equals(productNo)){
                return list[i];
            }
        }
        return null;
    }

    @Override
    public Product[] selectAll() {
        return list;
    }

    @Override
    public int getCount() {
        return count;
    }
}
