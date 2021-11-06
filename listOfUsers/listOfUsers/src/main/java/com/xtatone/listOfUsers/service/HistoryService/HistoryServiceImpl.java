package com.xtatone.listOfUsers.service.HistoryService;

import com.xtatone.listOfUsers.dao.HistoryRepository;
import com.xtatone.listOfUsers.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void saveHistory(History history) {
        historyRepository.save(history);
    }
}
