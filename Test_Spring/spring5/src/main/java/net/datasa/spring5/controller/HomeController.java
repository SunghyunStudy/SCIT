package net.datasa.spring5.controller;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {
	@GetMapping({ "", "/" })
	public String home() {

		return "home";
	}

	@GetMapping("thymeleaf")
	public String thymeleaf(
			@AuthenticationPrincipal UserDetails user // authenticationUser 객체로 부터 뭘 가져옴...?
	) {

		/**
		 * @AuthenticationPrincipal
		 *                          - Spring Security가 직접 로그인 한 사용자 정보를 주입해줌
		 *                          - 로그인 성공 -> SecurityContext에 Authentication 객체 저장
		 *                          - Controller 에서 Authentication 객체에 접근
		 */
		if (user != null) {
			log.debug("== Authentication 객체 정보 출력 ==");
			log.debug(user.getUsername());
			log.debug(user.getPassword());
			log.debug("" + user.getAuthorities());
			log.debug("" + user.isAccountNonExpired());
			log.debug("" + user.isAccountNonLocked());
			log.debug("" + user.isCredentialsNonExpired());
			log.debug("" + user.isEnabled());
		} else {
			log.debug("로그인 안 된 상태");
		}
		return "thymeleaf";
	}

	@Value("${board.uploadPath}")
	String uploadPath;

	@GetMapping("file")
	public String file() {
		/*
		 * IOStream : 데이터를 읽고 쓰기 위한 통로
		 * File : 자바에서 파일이나 폴더의 존재 여부 확인, 생성, 삭제, 이름 변경, 경로 정보 확인 등을 할 때 사용되는 유틸리티 클래스
		 * 
		 * exists() 파일이나 폴더가 존재하는지 확인
		 * isFile() 이 경로가 파일인지 확인
		 * isDirectory() 이 경로가 폴더인지 확인
		 * mkdir() 폴더 하나 생성
		 * createNewFile() 빈 파일 생성
		 * delete() 파일이나 폴더 삭제
		 * getName() 파일 또는 폴더 이름 반환
		 * getPath(), getAbsolutePath() 경로 문자열 반환
		 */

		File dir = new File(uploadPath, "myfolder");

		// 폴더 생성 : mkdirs() 중간 폴더까지 한번에 생성
		if (!dir.exists()) {
			boolean ok = dir.mkdirs();
			System.out.println("폴더 생성 여부 : " + ok + " > " + dir.getAbsolutePath());

			if (!ok)
				return "redirect:/";
		}

		// 파일 객체 생성
		File file = new File(dir, "example.txt");

		System.out.println("[파일 정보]");
		System.out.println("이름 : " + file.getName());
		System.out.println("경로(절대): " + file.getAbsolutePath());
		System.out.println("존재 여부: " + file.exists());

		String content = """
				안녕하세요!
				IOStream으로 파일에 저장합니다.
				""";

		// OutputStream은 추상 클래스 FileOutputStream은 구현 클래스 : 쓰기 기능을 함.
		// Scanner와 다르게 아래 얘네들은 자동으로 close()를 해줌.
		try (OutputStream out = new FileOutputStream(file);
				Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(content);
			bw.flush(); // 비어있는 데이터를 강제 출력
			System.out.println("파일 쓰기 완료");

		} catch (IOException e) {
			System.out.println("파일 읽기 실패 : " + e.getMessage());
		}

		// 파일 읽기
		try (InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(reader)) {

			System.out.println("\n[파일 내용 읽기]");
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			System.out.println("파일 읽기 실패 : " + e.getMessage());
		}

		// 정보 출력
		System.out.println("\n[메타 정보]");
		System.out.println("파일 크기(byte): " + file.length());
		System.out.println("마지막 수정(ms) " + file.lastModified());

		// 삭제
		// boolean deleted = file.delete();
		// System.out.println("\n파일 삭제 여부 : " + deleted);

		return "redirect:/";
	}
}
