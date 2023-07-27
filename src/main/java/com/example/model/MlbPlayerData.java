package com.example.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
/* csv file format
"Name", "Team", "Position", "Height(inches)", "Weight(lbs)", "Age"
 */
public class MlbPlayerData {

    private String name;

    private String team;

    private String position;

    private Integer height;

    private Integer weight;

    private Float age;
}
