package com.example.shopproj.dto;

import com.example.shopproj.constant.ItemSellStatus;
import com.example.shopproj.entity.ItemImg;
import com.example.shopproj.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO{

    private Long id; // 상품코드

    @NotBlank
    @Size(max = 50, min = 2, message = "상품명은 2~50자 입니다.")
    private String itemNm; // 상품명

    @NotNull
    @PositiveOrZero
    private int price;      // 가격

    @NotNull
    @PositiveOrZero
    private int stockNumber;    // 재고수량

    @NotBlank
    private String itemDetail;  // 상품 상세설명
    // 상품 판매 상태

    private String createBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    private List<ItemImgDTO> itemImgDTOList;

    public  ItemDTO setItemImgDTOList(List<ItemImg> itemImgList) {

        ModelMapper modelMapper = new ModelMapper();

        List<ItemImgDTO> itemImgDTOS =
        itemImgList.stream().map(itemImg -> modelMapper.map(itemImg, ItemImgDTO.class)).collect(Collectors.toList());

        this.itemImgDTOList = itemImgDTOS;

        return this;
    }
}
