package net.datasa.spring6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.spring6.domain.dto.PersonDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class Ajax2Controller {

    // response어쩌구가 없으면 return으로 html로 이동하는거임
    @GetMapping("ajax2")
    public String ajax2() {
        return "ajax2";
    }

    @ResponseBody
    @PostMapping("insert1")
    public void insert1(@RequestParam("name") String name,
            @RequestParam("age") String age,
            @RequestParam("phone") String phone) {
        log.debug("insert1() : name={}, age={}, phone={}", name, age, phone);
    }

    @ResponseBody
    @PostMapping("insert2")
    public void insert2(PersonDTO person) {
        log.debug("insert2() : {}", person);
    }

    @ResponseBody
    @PostMapping("insert3")
    public void insert3(PersonDTO person) {
        log.debug("insert3() : {}", person);
    }

    @ResponseBody
    @GetMapping("getObject")
    public PersonDTO getObject() {
        PersonDTO person = new PersonDTO("홍길동", 20, "010-1010-2121");

        return person;
    }

    @ResponseBody
    @GetMapping("getList")
    public List<PersonDTO> getList() {
        List<PersonDTO> list = new ArrayList<>();
        list.add(new PersonDTO("홍길동", 20, "010-1010-2121"));
        list.add(new PersonDTO("김철수", 30, "010-2222-2222"));
        list.add(new PersonDTO("이순신", 40, "010-3333-3333"));

        return list;
    }

    @ResponseBody
    @PostMapping("getJson")
    // @RequestBody : 보낸 객체가 문자열이어도 Json 형식이라면 이걸 붙여줌.
    public String getJson(
            // @RequestBody String personList,
            @RequestBody List<PersonDTO> personDTO) throws Exception {
        log.debug("전달받은 문자열: {}", personDTO);

        // ObjectMapper : JSON <-> Java 객체 변환을 담당하는 Jackson 라이브러리 클래스
        // JSON 객체의 직렬화 : 자바객체를 JSON 형식의 문자열로 반환
        ObjectMapper om = new ObjectMapper();

        // 아래는 List가 아닌 String으로 받아왔을 때 사용한 문장임.
        // JSON -> Java 객체 readValue()
        // List<PersonDTO> list = om.readValue(personList, new
        // TypeReference<List<PersonDTO>>() {
        // });

        // for (PersonDTO p : list) {
        // log.debug("list : {}", p);
        // }

        // Java 객체 -> JSON writeValueAsString()
        List<PersonDTO> plist = new ArrayList<>();
        plist.add(new PersonDTO("홍길동", 20, "010-1010-2121"));
        plist.add(new PersonDTO("김철수", 30, "010-2222-2222"));
        plist.add(new PersonDTO("이순신", 40, "010-3333-3333"));
        plist.add(new PersonDTO("조조조", 40, "010-3333-3333"));
        String jsonString = om.writeValueAsString(plist);
        return jsonString;
    }

}
