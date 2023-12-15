package com.LogbookApp.service;

import com.LogbookApp.dto.DropdownDTO;
import com.LogbookApp.dto.logbook.*;
import com.LogbookApp.entity.LogBook;
import com.LogbookApp.entity.Notification;
import com.LogbookApp.repository.AccountRepository;
import com.LogbookApp.repository.LogRepository;
import com.LogbookApp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Page<EmployeeLogListDTO> getDataForEmployee(String username, Integer submissionMonth, Integer submissionYear, boolean clientApproval, boolean hrdApproval, Integer period, Integer page) {
        var pageable = PageRequest.of(page - 1, 30, Sort.by("logDate"));
        var data = logRepository.getDataForEmployee(username, submissionMonth, submissionYear, clientApproval, hrdApproval, period, pageable);
        return data;
    }

    public EmployeeLogHeaderDTO getHeader(String username) {
        var data = logRepository.getEmployeeLogHeader(username);
        return data;
    }

    public Page<LogListDTO> getData(String username, Integer submissionMonth, Integer submissionYear, boolean clientApproval, boolean hrdApproval, Integer page) {
        var pageable = PageRequest.of(page - 1, 30, Sort.by("logDate"));
        var data = logRepository.getData(username, submissionMonth, submissionYear, clientApproval, hrdApproval, pageable);
        return data;
    }

    public Page<ClientEmployeeLogDTO> getClientEmployeeLog(String clientUsername, String username, Integer logMonth, Integer logYear, boolean clientApproval, Integer page) {
        var pageable = PageRequest.of(page - 1, 30, Sort.by("logDate"));
        var data = logRepository.getClientEmployeeLog(clientUsername, username, logMonth, logYear, clientApproval, pageable);
        return data;
    }

    public List<DropdownDTO> getMonthDropdown() {
        return DropdownDTO.getMonthDropdown();
    }

    public List<DropdownDTO> getPeriodDropdown() {
        return logRepository.getPeriodDropdown();
    }

    public List<DropdownDTO> getYearDropdown() {
        return logRepository.getYearDropdown();
    }

    public List<DropdownDTO> getLogDateYearDropdown() {
        return logRepository.getLogDateYearDropdown();
    }

    public List<DropdownDTO> getStatus() {
        return DropdownDTO.getStatusDropdown();
    }

    public List<DropdownDTO> getEmployeeDropdown() {
        return accountRepository.employeeDropdown();
    }

    public List<DropdownDTO> getClientDropdown() {
        return accountRepository.clientDropdown();
    }

    public List<DropdownDTO> getHRDDropdown() {
        return accountRepository.hrdDropdown();
    }

    public void insertNew(InsertNewLogDTO dto) {
        LocalDate currentDate = LocalDate.now();

        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            var entity = new LogBook();
            entity.setId(dto.getId());
            entity.setUsername(dto.getUsername());
            entity.setClientUsername(dto.getClientUsername());
            entity.setHrdUsername(dto.getHrdUsername());
            entity.setClientApproval(dto.getClientApproval());
            entity.setHrdApproval(dto.getHrdApproval());
            entity.setSubmissionDate(currentDate);
            entity.setLogDate(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day));
            entity.setPeriod(dto.getPeriod());

            logRepository.save(entity);
        }
    }

    public EditLogDTO findOne(Long id) {
        var log = logRepository.findById(id).get();
        var dto = new EditLogDTO(
                log.getId(),
                log.getUsername(),
                log.getNote(),
                log.getClientApproval(),
                log.getHrdApproval(),
                log.getClientUsername(),
                log.getHrdUsername(),
                log.getSubmissionDate(),
                log.getActivity(),
                log.getLogDate()
        );
        return dto;
    }

    public void saveLog(EditLogDTO dto) {
        var entity = new LogBook();
        var log = logRepository.findById(dto.getId()).get();
        entity.setId(log.getId());
        entity.setUsername(log.getUsername());
        entity.setNote(dto.getNote());
        entity.setClientApproval(log.getClientApproval());
        entity.setHrdApproval(log.getHrdApproval());
        entity.setClientUsername(log.getClientUsername());
        entity.setHrdUsername(log.getHrdUsername());
        entity.setSubmissionDate(LocalDate.now());
        entity.setActivity(dto.getActivity());
        entity.setLogDate(log.getLogDate());
        entity.setPeriod(log.getPeriod());
        logRepository.save(entity);
    }

    public void hrdApprove(Long id) {
        var notif = new Notification();
        var log = logRepository.findById(id).get();
        log.setHrdApproval(true);
        log.setHrdApprovalDate(LocalDate.now());
        notif.setLogId(log.getId());
        notif.setHrdApprovalNotification(true);
        notif.setClientApprovalNotification(true);
        notif.setIsRead(false);
        notif.setMessage("Logbook sudah diapprove oleh HRD");
        notif.setNotificationDate(LocalDateTime.now());
        notificationRepository.save(notif);
        logRepository.save(log);
    }

    public void hrdApproveAll() {
        var logs =  logRepository.hrdApproveAll();
        var notif = new Notification();

        for (var log : logs) {
            var approve = logRepository.findById(log.getId()).get();
            approve.setHrdApproval(true);
            approve.setHrdApprovalDate(LocalDate.now());
            notif.setLogId(log.getId());
            notif.setHrdApprovalNotification(true);
            notif.setClientApprovalNotification(true);
            notif.setIsRead(false);
            notif.setMessage("Logbook sudah diapprove oleh HRD");
            notif.setNotificationDate(LocalDateTime.now());
            notificationRepository.save(notif);
            logRepository.save(approve);
        }
    }

    public void clientApprove(Long id) {
        var notif = new Notification();
        var log = logRepository.findById(id).get();
        log.setClientApproval(true);
        log.setClientApprovalDate(LocalDate.now());
        notif.setLogId(log.getId());
        notif.setClientApprovalNotification(true);
        notif.setHrdApprovalNotification(false);
        notif.setIsRead(false);
        notif.setMessage("Logbook sudah diapprove oleh Client");
        notif.setNotificationDate(LocalDateTime.now());
        notificationRepository.save(notif);
        logRepository.save(log);
    }

    public void clientApproveAll() {
        var logs =  logRepository.clientApproveAll();
        var notif = new Notification();

        for (var log : logs) {
                var approve = logRepository.findById(log.getId()).get();
                approve.setClientApproval(true);
                approve.setClientApprovalDate(LocalDate.now());
                notif.setLogId(log.getId());
                notif.setClientApprovalNotification(true);
                notif.setHrdApprovalNotification(false);
                notif.setIsRead(false);
                notif.setMessage("Logbook sudah diapprove oleh Client");
                notif.setNotificationDate(LocalDateTime.now());
                notificationRepository.save(notif);
                logRepository.save(approve);
        }
    }
    public LogDetailDTO logDetail(Long id) {
        var entity = logRepository.findById(id).get();
        var dto = new LogDetailDTO(
                entity.getLogDate(),
                entity.getNote(),
                entity.getActivity()
        );
        return dto;
    }
}
