package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.AwsDto;
import com.commerce.song.domain.dto.ProductDto;
import com.commerce.song.domain.dto.ProductOptionDto;
import com.commerce.song.domain.dto.ResultDto;
import com.commerce.song.domain.entity.*;
import com.commerce.song.exception.AwsUploadException;
import com.commerce.song.exception.BadRequestException;
import com.commerce.song.repository.*;
import com.commerce.song.service.AwsService;
import com.commerce.song.service.ProductService;
import com.commerce.song.util.CustomUtil;
import com.commerce.song.util.enums.comm.CommImgType;
import com.commerce.song.util.enums.rescode.AwsCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final VenderRepository venderRepository;
    private final CommImgRepository commImgRepository;
    private final ProductOptionRepository productOptionRepository;
    private final AwsService awsService;

    @Override
    public Page<ProductDto.ResList> findAll(ProductDto.ReqList requestDto) {
        return productRepository.findAllToDtoPage(CustomUtil.convertPageVo(requestDto), requestDto);
    }
    @Override
    @Transactional
    public Long createProduct(ProductDto.createProductReq reqDto) {
        Category category = categoryRepository.findById(reqDto.getCategoryId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 카테고리입니다."));

        Brand brand = brandRepository.findById(reqDto.getBrandId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 브랜드입니다."));

        Vender vender = venderRepository.findById(reqDto.getVdrId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 벤더사입니다."));

        // 상품 save 로직
        Product product = CustomUtil.convertClass(reqDto, Product.class);
        product.of(category, brand, vender);
        product.setSaleDate(reqDto.getStartDate(), reqDto.getEndDate());
        Product savedProduct = productRepository.save(product);

        // files 업로드 로직
        List<MultipartFile> files = reqDto.getFiles();
        if(files.size() > 5) throw new BadRequestException("이미지 업로드는 5개까지 가능합니다.");

        for(int fileIndex = 0; fileIndex < files.size(); fileIndex++) {
            MultipartFile file = files.get(fileIndex);
            AwsDto.ImageUploadReq uploadReq = new AwsDto.ImageUploadReq();
            uploadReq.setFolder("product");
            uploadReq.setImage(file);
            try {
                ResultDto<AwsDto.FileUploadRes> uploadResult = awsService.uploadImage(uploadReq);
                CommImg img = CommImg.builder()
                        .refId(savedProduct.getProductId())
                        .tp(CommImgType.PRODUCT.getCode())
                        .seq(fileIndex)
                        .imgPath(uploadResult.getResultData().getFileUrl())
                        .awsPath(uploadResult.getResultData().getAwsUrl())
                        .build();
                commImgRepository.save(img);

            } catch(IOException e) {
                throw new AwsUploadException(AwsCode.AWS_FILE_UPLOAD_FAILED);
            }
        }

        // 임시저장된 이미지들 확정처리
        for(Long imgId : CustomUtil.safeList(reqDto.getUploadImgIds())) {
            commImgRepository.updateConfirmTypeByImgId(imgId);
        }

        // 옵션 등록
//        Integer optType = reqDto.getOptType();
        List<ProductOptionDto.CreateProductOptionReq> optionList = reqDto.getOptionList();
        for(ProductOptionDto.CreateProductOptionReq option : CustomUtil.safeList(optionList)) {
            ProductOption productOption = CustomUtil.convertClass(option, ProductOption.class);
            productOption.setProduct(savedProduct);
            productOptionRepository.save(productOption);
        }

        return savedProduct.getProductId();
    }
    @Override
    public ResultDto<ProductDto.Res> findById(Long id) {
        Product product = productRepository.findProductEagerById(id)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 상품입니다."));
        ProductDto.Res res = CustomUtil.convertClass(product, ProductDto.Res.class);

        return ResultDto.res(res);
    }
}
