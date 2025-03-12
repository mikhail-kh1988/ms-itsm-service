package com.itsm.userservicemanagment.entity.file;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User createBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;
}
