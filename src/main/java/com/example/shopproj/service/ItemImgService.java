package com.example.shopproj.service;

import com.example.shopproj.entity.Item;
import com.example.shopproj.entity.ItemImg;
import com.example.shopproj.repository.ItemImgRepository;
import com.example.shopproj.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ItemImgService {

    private final ItemImgRepository itemImgRepository;
    private final ItemRepository itemRepository;
    private final FileService fileService;


    // 이미지 등록
    public void saveImg(Long id, List<MultipartFile> multipartFile) throws IOException {
        //이미지 등록은 어디에 무엇을 할 것인가
        //이미지 아이템꺼
        //아이템pk 이미지 파일 이미지파일을 경로를 잘라서
        // 경로와 함께 이름을 저장한다.
        log.info("아이템 이미지 서비스로 들어온 id" + id);
        if (multipartFile != null){
            for (MultipartFile img : multipartFile){
                if (!img.isEmpty()){
                    log.info("아이템 이미지 서비스로 들어온 이미지" + img.getOriginalFilename());
                    // 물리적인 저장

                    String savedFileName =      //uuid가 포함된 물리적인 파일이름
                    fileService.uploadFile(img);

                    // db 저장
                    // 엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다. 해볼것
                    Item item =
                    itemRepository.findById(id).get();

                    String imgUrl = "/images/item/" + savedFileName;

                    ItemImg itemImg = new ItemImg();
                    itemImg.setItem(item);      // 본문 // 이미지가 달릴 아이템
                    itemImg.setImgName(savedFileName);       //uuid 포함 저장될 이름
                    itemImg.setImgUrl(imgUrl);        // 경로
                    itemImg.setOriImgName(img.getOriginalFilename());    // 원래이름
                    // 대표이미지 여부 확인
                    if (multipartFile.indexOf(img) == 0){
                        itemImg.setRepimgYn("Y");      //  대표이미지

                    }else {
                        itemImg.setRepimgYn("N");
                    }

                    itemImgRepository.save(itemImg);

                }
            }
        }
    }

    public void update(Long id, List<MultipartFile> multipartFile, Long mainino) throws IOException {


        if (multipartFile != null){
            for (MultipartFile img : multipartFile){
                if (!img.isEmpty()){
                    log.info("아이템 이미지 서비스로 들어온 이미지" + img.getOriginalFilename());
                    // 물리적인 저장

                    String savedFileName =      //uuid가 포함된 물리적인 파일이름
                            fileService.uploadFile(img);

                    // db 저장
                    // 엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다. 해볼것
                    Item item =
                            itemRepository.findById(id).get();

                    String imgUrl = "/images/item/" + savedFileName;

                    ItemImg itemImg = new ItemImg();
                    itemImg.setItem(item);      // 본문 // 이미지가 달릴 아이템
                    itemImg.setImgName(savedFileName);       //uuid 포함 저장될 이름
                    itemImg.setImgUrl(imgUrl);        // 경로
                    itemImg.setOriImgName(img.getOriginalFilename());    // 원래이름
                        //대표이미지 여부확인
                    if (mainino == null){
                        if (multipartFile.indexOf(img) == 0){

                            itemImg =
                            itemImgRepository.findByItemIdAndRepimgYn(id, "Y");
                            itemImg.setItem(item);      // 본문 // 이미지가 달릴 아이템
                            itemImg.setImgName(savedFileName);       //uuid 포함 저장될 이름
                            itemImg.setImgUrl(imgUrl);        // 경로
                            itemImg.setOriImgName(img.getOriginalFilename());    // 원래이름

//                            itemImg.setRepimgYn('Y'); // 대표이미지
                        }else {
                            itemImg.setRepimgYn("N");
                        }

                    }else {
                        itemImg.setRepimgYn("N");
                    }

                    itemImgRepository.save(itemImg);

                }
            }
        }
    }
    public void removeImg(Long id){
        // 물리적 파일을 삭제하기 위해서
        // 데이터를 가져온다.
        ItemImg itemImg =
        itemImgRepository.findById(id).get();
        // 저장경로 value 위에 있는 value 이미 이건 파일서비스에 있어

        // 저장경로 value 위에 있는 value

        // 저장 파일명


        // 물리적 파일 삭제를 먼저한다.
        // 삭제하려면 경로 및 사진파일명이 필요
        fileService.removefile(itemImg.getImgName());
        //반환값에 따라 물리파일이 삭제가 되었다면 안되었다면에 따라
        //db를 지우거나 지우지 않거나

        // db에서 값을 지운다
        itemImgRepository.deleteById(id);

    }
}
