package com.LogbookApp.repository;

import com.LogbookApp.dto.DropdownDTO;
import com.LogbookApp.dto.logbook.*;
import com.LogbookApp.entity.LogBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends JpaRepository<LogBook, Long> {

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.LogListDTO(
                log.id,
                log.username,
                log.logDate,
                log.clientApproval,
                log.clientApprovalDate,
                log.hrdApproval,
                log.hrdApprovalDate,
                cli.name,
                hrd.name,
                log.note,
                log.activity
            )
            FROM LogBook AS log
                JOIN log.clientAccount AS cli
                JOIN log.hrdAccount AS hrd
            WHERE log.username = :username
                AND (:submissionMonth IS NULL OR MONTH(log.submissionDate) = :submissionMonth)
                AND (:submissionYear IS NULL OR YEAR(log.submissionDate) = :submissionYear)
                AND (:clientApproval IS NULL OR log.clientApproval = :clientApproval)
                AND (:hrdApproval IS NULL OR log.hrdApproval = :hrdApproval)
            """)

    public Page<LogListDTO> getData(@Param("username") String username,
                                    @Param("submissionMonth") Integer submissionMonth,
                                    @Param("submissionYear") Integer submissionYear,
                                    @Param("clientApproval") boolean clientApproval,
                                    @Param("hrdApproval") boolean hrdApproval,
                                    Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.EmployeeLogListDTO(
                log.id,
                log.username,
                log.logDate,
                log.clientApproval,
                log.hrdApproval,
                cli.name,
                hrd.name,
                log.activity,
                log.note
            )
            FROM LogBook AS log
                JOIN log.clientAccount AS cli
                JOIN log.hrdAccount AS hrd
            WHERE log.username = :username
                AND (:logMonth IS NULL OR MONTH(log.logDate) = :logMonth)
                AND (:logYear IS NULL OR YEAR(log.logDate) = :logYear)
                AND (:clientApproval IS NULL OR log.clientApproval = :clientApproval)
                AND (:hrdApproval IS NULL OR log.hrdApproval = :hrdApproval)
                AND (:period IS NULL OR log.period = :period)
            """)

    public Page<EmployeeLogListDTO> getDataForEmployee(@Param("username") String username,
                                                       @Param("logMonth") Integer logMonth,
                                                       @Param("logYear") Integer logYear,
                                                       @Param("clientApproval") boolean clientApproval,
                                                       @Param("hrdApproval") boolean hrdApproval,
                                                       @Param("period") Integer period,
                                                       Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.ClientEmployeeLogDTO(
                log.id,
                log.username,
                log.logDate,
                log.clientApproval,
                log.clientApprovalDate,
                log.note,
                log.activity
            )
            FROM LogBook AS log
                JOIN log.clientAccount AS cli
                JOIN log.hrdAccount AS hrd
            WHERE cli.username = :clientUsername
                AND log.username = :username
                AND (:logMonth IS NULL OR MONTH(log.logDate) = :logMonth)
                AND (:logYear IS NULL OR YEAR(log.logDate) = :logYear)
                AND (:clientApproval IS NULL OR log.clientApproval = :clientApproval)
            """)

    public Page<ClientEmployeeLogDTO> getClientEmployeeLog(@Param("clientUsername") String clientUsername,
                                                           @Param("username") String username,
                                                           @Param("logMonth") Integer logMonth,
                                                           @Param("logYear") Integer logYear,
                                                           @Param("clientApproval") boolean clientApproval,
                                                           Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                YEAR(log.submissionDate),
                YEAR(log.submissionDate)
            )
            FROM LogBook AS log
                GROUP BY log.submissionDate
            """)
    public List<DropdownDTO> getYearDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                log.period,
                log.period
            )
            FROM LogBook AS log
                WHERE log.period IS NOT NULL
                GROUP BY log.period
            """)
    public List<DropdownDTO> getPeriodDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                MAX(YEAR(log.logDate)),
                MAX(YEAR(log.logDate))
            )
            FROM LogBook AS log
                GROUP BY YEAR(log.logDate)
            """)
    public List<DropdownDTO> getLogDateYearDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.EmployeeLogHeaderDTO(
                log.clientUsername,
                cli.name
            )
            FROM LogBook AS log
                JOIN log.clientAccount AS cli
            WHERE log.username = :username
            GROUP BY log.clientUsername, cli.name
            """)
    public EmployeeLogHeaderDTO getEmployeeLogHeader(@Param("username") String username);

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.LogApproveAllDTO(
                log.id
            )
            FROM LogBook AS log
            WHERE clientApproval = FALSE
            AND log.note IS NOT NULL
            """)
    public List<LogApproveAllDTO> clientApproveAll();

    @Query("""
            SELECT new com.LogbookApp.dto.logbook.LogApproveAllDTO(
                log.id
            )
            FROM LogBook AS log
            WHERE clientApproval = TRUE
            AND hrdApproval = FALSE
            AND log.note IS NOT NULL
            """)
    public List<LogApproveAllDTO> hrdApproveAll();
}
