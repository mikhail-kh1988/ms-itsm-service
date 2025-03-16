package com.itsm.userservicemanagment.entity.incident;

import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.file.File;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncidentAppendFile {

    private Long id;
    private Incident incident;
    private File appendFile;
    private LocalDateTime createDate;
    private User createBy;
    private String appendFileName;

}
