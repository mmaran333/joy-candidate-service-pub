package com.bayada.joy.audit;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auditable<U> {

  @CreatedBy
  private U createdBy;

  @CreatedDate
  private LocalDateTime creationDate;

  @LastModifiedBy
  private U lastModifiedBy;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}

