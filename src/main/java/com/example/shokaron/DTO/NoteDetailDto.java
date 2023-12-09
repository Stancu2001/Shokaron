package com.example.shokaron.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDetailDto {
    private Long id;
    private String numeStudent;
    private String clasa;
    private String materie;
    private int nota;
}
