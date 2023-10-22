package com.example.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "mlb_player_data")
/* csv file format
"Name", "Team", "Position", "Height(inches)", "Weight(lbs)", "Age"
 */
public class MlbPlayerData {

    @Id
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private Float age;
    @Column
    @NotNull
    private Integer height;
    @Column
    @NotNull
    private String position;
    @Column
    @NotNull
    private String team;
    @Column
    @NotNull
    private Integer weight;

}
