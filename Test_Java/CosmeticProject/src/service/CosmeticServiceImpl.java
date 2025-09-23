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


        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(String productNo) {
        return false;
    }

    @Override
    public Product selectOne(String productNo) {
        return null;
    }

    @Override
    public Product[] selectAll() {
        return new Product[0];
    }

    @Override
    public int getCount() {
        return 0;
    }
}
