package org.mbc.board.controller;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class MemoController {

    //http://192.168.111.105:80/hello
    //http://192.168.0.171:6805/hello
    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("MemoController.hello() in........");

        model.addAttribute("msg","java last");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        var list = Arrays.asList("김", "이", "박", "최");

        model.addAttribute("list", list);
    }

    @ToString
    class SampleDTO {
        private String p1, p2, p3;



        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
    }

    //http://192.168.111.105:80/ex/ex2
    //http://192.168.0.171:6805/ex/ex2
    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        log.info("MemoController.ex2() in........");

        var strList = IntStream.range(1, 10)
                .mapToObj(i -> "데이터"+i)
                .collect(Collectors.toList());

        Map<String, String> map = new HashMap<>();
        map.put("id", "kkw");
        map.put("pw", "1234");

        var sample = new SampleDTO();
        sample.p1 = "값 ... p1";
        sample.p2 = "값 ... p2";
        sample.p3 = "값 ... p3";

        model.addAttribute("list", strList);
        model.addAttribute("map", map);
        model.addAttribute("dto", sample);
    }
}
