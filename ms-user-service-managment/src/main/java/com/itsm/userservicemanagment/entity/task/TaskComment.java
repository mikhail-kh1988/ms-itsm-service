package com.itsm.userservicemanagment.entity.task;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task_comment")
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Comment comment;

    private LocalDateTime createDate;
    private String externalId;

}
