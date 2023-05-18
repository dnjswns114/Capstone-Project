package com.hooong.simpleMember.Repository;

import com.hooong.simpleMember.Domain.Notice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeRepository {

    private static Map<Integer, Notice> notices = new HashMap<>();
    private static int sequence = 0;

    public void save(Notice notice) {
        notice.setId(++sequence);
        notices.put(notice.getId(), notice);
    }

    public void edit(int id, Notice notice) {
        notices.put(id, notice);
    }

    public void delete(int id) {
        notices.remove(id);
    }

    public List<Notice> findAll() {
        return new ArrayList<>(notices.values());
    }

    public Notice findById(int id) {
        return notices.get(id);
    }
}