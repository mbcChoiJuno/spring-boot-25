package org.mbc.board.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> bindExceptionHandler(BindException e) {

		log.info(e);
		Map<String, String> errorMap = new HashMap<String, String>();

		if (e.hasErrors()) {
			var bindingResult = e.getBindingResult();

			bindingResult.getFieldErrors().forEach(fieldError -> {
				log.error(fieldError);
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			});
		}
		return ResponseEntity.badRequest().body(errorMap);
//		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	// 500에러 처리
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> FKExceptionHandler(Exception e) {
		log.error(e);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("time:", ""+System.currentTimeMillis());
		errorMap.put("에러메세지:", "sql문이나 data 오류");

		return ResponseEntity.badRequest().body(errorMap);
	}

	@ExceptionHandler({
			NoSuchElementException.class,
			EmptyResultDataAccessException.class
	})
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> NoSuchElementExceptionHandler(Exception e) {
		log.error(e);

		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("time:", "" + System.currentTimeMillis());
		errorMap.put("에러메세지:", "없는 데이터 접근");

		return ResponseEntity.badRequest().body(errorMap);
	}
}
