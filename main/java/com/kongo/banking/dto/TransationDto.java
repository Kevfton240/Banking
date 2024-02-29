package com.kongo.banking.dto;


import com.kongo.banking.models.TransactionType;
import com.kongo.banking.models.Transation;
import com.kongo.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransationDto {

    private Integer id;

    @Positive
    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private Integer userId;

    public static TransationDto fromEntity(Transation transation){
        return TransationDto.builder()
                .id(transation.getId())
                .amount(transation.getAmount())
                .type(transation.getType())
                .destinationIban(transation.getDestinationIban())
                .userId(transation.getUser().getId())
                .build();
    }

   public static Transation toEntity(TransationDto transaction){
        return  Transation.builder()
                .id((transaction.getId()))
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user(User.builder().id(transaction.getId()).build())
                .build();
   }

}
