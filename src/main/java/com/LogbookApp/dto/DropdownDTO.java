package com.LogbookApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DropdownDTO {
    private Object value;
    private Object textContent;

    public static List<DropdownDTO> getMonthDropdown() {
        var dropdownDTO = new ArrayList<DropdownDTO>();
        dropdownDTO.add(new DropdownDTO("1", "January"));
        dropdownDTO.add(new DropdownDTO("2", "February"));
        dropdownDTO.add(new DropdownDTO("3", "March"));
        dropdownDTO.add(new DropdownDTO("4", "April"));
        dropdownDTO.add(new DropdownDTO("5", "May"));
        dropdownDTO.add(new DropdownDTO("6", "June"));
        dropdownDTO.add(new DropdownDTO("7", "July"));
        dropdownDTO.add(new DropdownDTO("8", "August"));
        dropdownDTO.add(new DropdownDTO("9", "September"));
        dropdownDTO.add(new DropdownDTO("10", "October"));
        dropdownDTO.add(new DropdownDTO("11", "November"));
        dropdownDTO.add(new DropdownDTO("12", "December"));
        return dropdownDTO;
    }

    public static List<DropdownDTO> getStatusDropdown() {
        var dropdownDTO = new ArrayList<DropdownDTO>();
        dropdownDTO.add(new DropdownDTO(true, "Approved"));
        dropdownDTO.add(new DropdownDTO(false, "Not Approved"));
        return dropdownDTO;
    }
}
