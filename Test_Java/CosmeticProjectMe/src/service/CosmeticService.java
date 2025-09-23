package service;

import vo.Product;

public interface CosmeticService {

    /** 문서 주석
     * 전달된 Product 객체(립스틱, 파운데이션) 데이터를 객체배열내에 삽입
     * @param product
     * @return 삽입 성공하면 true, 실패 시 false를 반환하도록.
     */
    public boolean insert(Product product);


    /**
     * 전달받은 Product 객체
     * @param product (립스틱, 파운데이션) 데이터를 객체배열내에서 찾은 후 값을 수정.
     * @return 수정 성공하면 true, 실패하면 false 반환
     */
    public boolean update(Product product);


    /**
     * 전달받은 productNo를 키로 하는 데이터를 배열에서 검색한 후
     * 검색된 데이터를 삭제
     * @param productNo : 검색할 키가 됨
     * @return 삭제 성공하면 true, 실패하면 false 반환
     */
    public boolean delete(String productNo);


    /**
     * 전달받은 productNo를 키로 하는 데이터를 배열에서 검색한 후
     * 검색된 데이터를 반환
     *
     * Product는 검색된 데이터, 검색을 실패하면 null 반환
     * @param productNo
     * @return
     */
    public Product selectOne(String productNo);


    /**
     * Product 배열 전체를 요청.
     * 배열 그 자체를 반환
     * @return
     */
    public Product[] selectAll();


    /**
     * 운영하는 배열내에 Product가 저장된 데이터의 개수
     * @return 데이터의 개수
     */
    public int getCount();

}
