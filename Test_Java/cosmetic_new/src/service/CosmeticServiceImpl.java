package service;

/**
 * 프로젝트의 실제 기능을 담당 -
 * 처리할 데이터를 가지고 있다가, UI 에서 넘어온 데이터를 처리하거나,
 * 사용자가 보고자 하는 데이터를 UI 로 전달한다.
 */

import vo.Foundation;
import vo.Lipstick;
import vo.Product;

public class CosmeticServiceImpl implements CosmeticService {
    Product[] list = new Product[50];    // 제품이 등록될 배열
    int count = 0;                       // 배열 내에 제품이 몇 개가 등록되었는지 카운트

    /**
     * 배열에 제품을 등록 (DB가 꽉차서 등록할 자리가 없을 때 false 반환)
     * @param product
     * @return
     */
    @Override
    public boolean insert(Product product) {
        if(count >= list.length) return false;

        list[count++] = product;
        return true;
    }

    @Override
    public boolean update(Product product) {
        int index = searchIndex(product.getProductNo());

        if(product instanceof Lipstick) {
            Lipstick lipstick = (Lipstick) product;
            Lipstick temp = (Lipstick) list[index];

            temp.setName(lipstick.getName());
            temp.setPrice(lipstick.getPrice());
            temp.setType(lipstick.getType());
            temp.setColor(lipstick.getColor());

            list[index] = temp;

        } else if(product instanceof Foundation) {
            Foundation foundation = (Foundation) product;
            Foundation temp = (Foundation) list[index];

            temp.setName(foundation.getName());
            temp.setPrice(foundation.getPrice());
            temp.setTexture(foundation.getTexture());

            list[index] = temp ;
        }
        return true;
    }

    @Override
    public boolean delete(String productNo) {
        int index = searchIndex(productNo);

        for(int i=0; i< count; ++i) {
            // 삭제할 데이터를 찾으면
            if(list[i].getProductNo().equals(productNo)) {
                // 삭제할 데이터의 뒷부분의 데이터를 앞으로 복사
                for(int j= i+1; j <count-1; ++j) {
                    list[j] = list[j+1];
                }
                --count;
                return true;
            }
        }

        return false;
    }

    @Override
    public int searchIndex(String productNo) {
        int index = -1;
        for(int i=0; i<count; ++i) {
            if(list[i].getProductNo().equals(productNo)) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * PK에 해당하는 제품을 검색하여 반환
     *
     * @param productNo
     * @return
     */
    @Override
    public Product selectOne(String productNo) {
        for(int i=0; i< count; ++i) {
            if(list[i].getProductNo().equals(productNo))
                return list[i];   // 정보를 찾아서 반환
        }
        return null;   // 정보를 못찾아서 null 반환
    }

    /**
     * 배열 전체를 반환
     * @return
     */
    @Override
    public Product[] selectAll() {
        return list;
    }

    /**
     * 상품의 등록 개수 반환
     * @return
     */
    @Override
    public int getCount() {
        return count;
    }
}
