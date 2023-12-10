package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardListController {

    private final QuestionService questionService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/list")
    public String showList(Model model) {
        /**
         * 게시글 리스트
         * 질문 DB 테이블 에 저장되어있는 데이터를 조회 하여 모델 에 저장.
         *
         */
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList" , questionList );

        return "/domain/home/home/list";
    }
}
