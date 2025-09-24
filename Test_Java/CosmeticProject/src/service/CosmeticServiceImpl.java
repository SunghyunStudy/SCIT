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


    /**
     * 배열에 제품을 등록
     * 중복된 정보는 selectOne을 ui의 inputProduct에서 한번 실행해서 확인했기 때문에 신경쓰지 않아도 됨.
     * 배열(DB)가 꽉차서 등록할 자리가 없을 때 false 반환
     * @param product
     * @return
     */
    @Override
    public boolean insert(Product product) {
        if(list.length <= count) return false;
        list[count++] = product;
        return true;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(String productNo) {
        return false;
    }

    /**
     * PK에 해당하는 제품을 검색하여 반환
     *
     * @param productNo
     * @return
     */
    @Override
    public Product selectOne(String productNo) { // productNo는 PK임.
        for(int i = 0; i < count; ++i){
            if(list[i].getProductNo().equals(productNo)){  // list[i] 를 굳이 Product p = new 이렇게 안하는 이유는 새로 생성할 이유가 없기 때문.
                return list[i];  // 찾아서 반환
            }
        }
        return null; // 못찾아서 null 반환
    }

    /**
     * 배열 전체를 반환
     * @return
     */
    @Override
    public Product[] selectAll() {
        return list;            // service에 지금 만들어져있는 DB 전체를 보내버리면 됨.
    }

    /**
     * 상품의 등록 개수 반환 -> 어따씀?
     * 전체 조회할 때 쓰기 위함인가
     * @return
     */
    @Override
    public int getCount() {
        return count;
    }
}
