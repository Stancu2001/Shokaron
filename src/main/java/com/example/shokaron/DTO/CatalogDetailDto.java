package com.example.shokaron.DTO;


import com.example.shokaron.Entity.Materie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDetailDto {
    private Long id;
    private String clasa;
    private List<MaterieDto> materie;
}
