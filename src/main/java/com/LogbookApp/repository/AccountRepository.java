package com.LogbookApp.repository;

import com.LogbookApp.dto.DropdownDTO;
import com.LogbookApp.dto.account.*;
import com.LogbookApp.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("""
            SELECT COUNT(ac.username)
            FROM Account AS ac
            WHERE ac.username = :username
            """)
    public Integer countExistingUser(@Param("username") String username);

    @Query("""
            SELECT new com.LogbookApp.dto.account.ProfileDTO(
                acc.username,
                acc.name,
                acc.birthDate,
                acc.position,
                acc.company,
                acc.role
            )
            FROM Account AS acc
            WHERE acc.username = :username
            """)
    public ProfileDTO getEmployeeProfile (@Param("username") String username);

    @Query("""
            SELECT new com.LogbookApp.dto.account.EmployeeListDTO(
                acc.username,
                acc.name,
                acc.birthDate,
                acc.position
            )
            FROM Account AS acc
            WHERE acc.role = 'Employee'
            """)
    public Page<EmployeeListDTO> getEmployee(Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                acc.username,
                acc.name
            )
            FROM Account AS acc
            WHERE acc.role = 'Employee'
            """)
    public List<DropdownDTO> employeeDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                acc.username,
                acc.name
            )
            FROM Account AS acc
            WHERE acc.role = 'Client'
            """)
    public List<DropdownDTO> clientDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.DropdownDTO(
                acc.username,
                acc.name
            )
            FROM Account AS acc
            WHERE acc.role = 'HRD'
            """)
    public List<DropdownDTO> hrdDropdown();

    @Query("""
            SELECT new com.LogbookApp.dto.account.ClientEmployeeListDTO(
                use.username,
                use.name,
                use.position
            )
            FROM LogBook AS log
            JOIN log.account AS use
            JOIN log.clientAccount AS cli
            WHERE cli.username = :username
            GROUP BY use.username, use.name, use.position
            """)
    public Page<ClientEmployeeListDTO> getClientEmployee(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.account.ClientListDTO(
                acc.username,
                acc.name,
                acc.company
            )
            FROM Account AS acc
            WHERE acc.role = 'Client'
                AND acc.company LIKE %:company%
            """)
    public Page<ClientListDTO> getClient(@Param("company") String company, Pageable pageable);

    @Query("""
            SELECT new com.LogbookApp.dto.account.HRDListDTO(
                acc.username,
                acc.name,
                acc.birthDate
            )
            FROM Account AS acc
            WHERE acc.role = 'HRD'
                AND acc.name LIKE %:name%
            """)
    public Page<HRDListDTO> getHRD(@Param("name") String name, Pageable pageable);
}
