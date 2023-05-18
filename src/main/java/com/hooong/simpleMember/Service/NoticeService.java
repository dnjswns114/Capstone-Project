package com.hooong.simpleMember.Service;

import com.hooong.simpleMember.Domain.Notice;
import com.hooong.simpleMember.Repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 글 작성
     */
    public void writeContent(Notice notice) {
        noticeRepository.save(notice);
    }

    /**
     * 글 수정
     */
    public void editContent(int id, String texts, String password) {
        Notice notice = noticeRepository.findById(id);
        if(!notice.getPassword().equals(password)) {
            return;
        }

        notice.setTexts(texts);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
        notice.setUpdateDate(formattedDate);

        noticeRepository.edit(id, notice);
    }

    /**
     * 글 삭제
     */
    public void deleteContent(int id, String password) {
        Notice notice = noticeRepository.findById(id);
        if(!notice.getPassword().equals(password)) {
            return;
        }
        noticeRepository.delete(id);
    }

    /**
     * 전체 글 조회
     */
    public List<Notice> getAllContents() {
        return noticeRepository.findAll();
    }

    /**
     * Id로 글 조회
     */
    public Notice getContent(int id) {
        return noticeRepository.findById(id);
    }
}