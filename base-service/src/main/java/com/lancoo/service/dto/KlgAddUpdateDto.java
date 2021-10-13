package com.lancoo.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class KlgAddUpdateDto {

	
    private Integer recId;

    private String imporKnCode;

  
    private String imporKnText;

 
    private Integer atLevel;

  
    private String parentKnCode;

   
    private String childrenKnCode;

  
    private String subject;
    
    private String markeChilds;
}
