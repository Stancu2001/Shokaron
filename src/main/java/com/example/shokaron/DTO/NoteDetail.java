package com.example.shokaron.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDetail {
    private Long id;
    private String numeStudent;
    private String materie;
    private String clasa;
    private int nota;
}
