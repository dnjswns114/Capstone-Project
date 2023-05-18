package com.hooong.simpleMember.Controller;

import com.hooong.simpleMember.Domain.Content;
import com.hooong.simpleMember.Service.ContentService;
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
public class ContentController {

    private final ContentService contentService;

    // 홈 화면
    @GetMapping(value = { "/content"})
    public String content(Model model) {
        model.addAttribute("contents", contentService.getAllContents());
        return "/board/content";
    }

    // 글 쓰기 화면
    @GetMapping("/content/write")
    public String writePage() {
        return "/board/write-page";
    }

    // 글 쓰기
    @PostMapping("/content/write")
    public String writeContent(Content content) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
        content.setUpdateDate( formattedDate );

        contentService.writeContent(content);
        return "redirect:/content";
    }

    // 글 보기 화면
    @GetMapping("/content/{id}")
    public String showContent(@PathVariable int id, Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "/board/content-page";
    }

    // 글 수정
    @PostMapping("/content/{id}")
    public String editContent(@PathVariable int id, Content content) {
        contentService.editContent(id, content.getTexts(), content.getPassword());
        return "redirect:/content";
    }

    // 글 삭제
    @PostMapping("/content/delete/{id}")
    public String deleteContent(@PathVariable int id, Content content) {
        contentService.deleteContent(id, content.getPassword());
        return "redirect:/content";
    }
}