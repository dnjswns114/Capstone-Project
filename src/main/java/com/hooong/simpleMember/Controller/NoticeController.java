package com.hooong.simpleMember.Controller;

import com.hooong.simpleMember.Domain.Notice;
import com.hooong.simpleMember.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 홈 화면
    @GetMapping(value = {"/notice"})
    public String home(Model model) {
        model.addAttribute("notices", noticeService.getAllContents());
        return "/notice/notice";
    }

    // 글 쓰기 화면
    @GetMapping("/notice/write")
    public String writePage() {
        return "/notice/write-notice";
    }

    // 글 쓰기
    @PostMapping("/notice/write")
    public String writeContent(Notice notice) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
        notice.setUpdateDate( formattedDate );

        noticeService.writeContent(notice);
        return "redirect:/notice";
    }

    // 글 보기 화면
    @GetMapping("/notice/{id}")
    public String showContent(@PathVariable int id, Model model) {
        model.addAttribute("notice", noticeService.getContent(id));
        return "/notice/notice-page";
    }

    // 글 수정
    @PostMapping("/notice/{id}")
    public String editContent(@PathVariable int id, Notice notice) {
        noticeService.editContent(id, notice.getTexts(), notice.getPassword());
        return "redirect:/notice";
    }

    // 글 삭제
    @PostMapping("/notice/delete/{id}")
    public String deleteContent(@PathVariable int id, Notice notice) {
        noticeService.deleteContent(id, notice.getPassword());
        return "redirect:/notice";
    }
}