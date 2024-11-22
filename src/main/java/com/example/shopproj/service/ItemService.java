package com.example.shopproj.service;

import com.example.shopproj.dto.ItemDTO;
import com.example.shopproj.dto.PageRequestDTO;
import com.example.shopproj.dto.PageResponseDTO;
import com.example.shopproj.entity.Item;
import com.example.shopproj.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final ItemImgService itemImgService;
    //이미지 등록할 itemimgservice 의존성추가


    //상품등록

    public Long saveItem(ItemDTO itemDTO, List<MultipartFile> multipartFile) throws IOException {

        Item item =
        modelMapper.map(itemDTO, Item.class);

        item=
        itemRepository.save(item);

        // 이미지등록 추가할 예정
        itemImgService.saveImg(item.getId(),multipartFile);

        return item.getId();
    }

    public ItemDTO
    read(Long id){
        Item item =
        itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        ItemDTO itemDTO =
        modelMapper.map(item, ItemDTO.class)
                .setItemImgDTOList(item.getItemImgList());

        return itemDTO;
    }

    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO, String email){
//        List<Item> list =
//                itemRepository.findAll();
//
//        List<ItemDTO> itemDTOList =
//                list().stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
//        return itemDTOList;
        Pageable pageable = pageRequestDTO.getPageable("id");

        Page<Item> itemPage =
        itemRepository.getAdminItemPage(pageRequestDTO, pageable, email);

        List<ItemDTO> itemDTOList =
        itemPage.getContent().stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ItemDTO> itemDTOPageResponseDTO
                = PageResponseDTO.<ItemDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(itemDTOList)
                .total((int)itemPage.getTotalElements())
                .build();

        return itemDTOPageResponseDTO;
    }
}
