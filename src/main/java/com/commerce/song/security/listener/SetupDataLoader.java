package com.commerce.song.security.listener;

import com.commerce.song.domain.entity.*;
import com.commerce.song.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Profile({"local"})
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final ResourcesRepository resourcesRepository;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final VenderRepository venderRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean alreadySetup = false;

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup) {
            return;
        }
        setupSecurityResources();
    }

    @Transactional
    void setupSecurityResources() {
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저");
        Role userRole = createRoleIfNotFound("ROLE_USER", "사용자");

        Set<Role> adminRoleSet = new HashSet<>();
        adminRoleSet.add(adminRole);
        adminRoleSet.add(managerRole);
        adminRoleSet.add(userRole);
        createResourceIfNotFound("/api/v1/products", "GET", adminRoleSet, "url");
        createResourceIfNotFound("/api/v1/accounts", "GET", adminRoleSet, "url");
        createResourceIfNotFound("/api/v1/aws/upload/image", "POST", adminRoleSet, "url");


        Set<Role> managerRoleSet = new HashSet<>();
        managerRoleSet.add(managerRole);
        managerRoleSet.add(userRole);

        Set<Role> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);
        Account adminAccount = createAccountIfNotFound("admin", "1111", "admin@gmail.com", "19941126", adminRoleSet, "01011111111");
        Account managerAccount = createAccountIfNotFound("manager", "1111", "manager@gmail.com", "19931111", managerRoleSet, "01011111111");
        Account userAccount = createAccountIfNotFound("user", "1111", "user@gmail.com", "19921122", userRoleSet, "01011111111");
        Account masterAccount = createAccountIfNotFound("master", "1111", "master@camping.kr", "19921122", userRoleSet, "01011111111");

        Vender vender1 = createVenderIfNotFound("테스트벤더", "0");
        Brand brand1 = createBrandIfNotFound("테스트브랜드", "테스트브랜드입니다.");
        Category category1 = createCategoryIfNotFound("카테고리1", null);
        Category category2 = createCategoryIfNotFound("카테고리2", category1);

//        Product product1 = createProductIfNotFound("테스트상품", LocalDateTime.now(), LocalDateTime.now(), 0,0 , 0, "상품설명이다", 5000, 4000, 2000, 1000, brand1, category2, vender1);


    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Resources createResourceIfNotFound(String resourceName, String httpMethod, Set<Role> roleSet, String resourceType) {
        Resources resources = resourcesRepository.findByResourceNmAndHttpMethod(resourceName, httpMethod);

        if (resources == null) {
            resources = Resources.builder()
                    .resourceNm(resourceName)
                    .roleSet(roleSet)
                    .httpMethod(httpMethod)
                    .resourceTp(resourceType)
                    .seq(count.incrementAndGet())
                    .build();
        }
        return resourcesRepository.save(resources);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccountIfNotFound(String userName, String password, String email, String birth, Set<Role> role, String phone) {
        Account account = accountRepository.findByEmail(email);

        if(account == null) {
            account = Account.builder()
                    .username(userName)
                    .email(email)
                    .birth(birth)
                    .phone(phone)
                    .password(passwordEncoder.encode(password))
                    .userRoles(role)
                    .build();
        }
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleNm(roleName);

        if(role == null) {
            role = Role.builder()
                    .roleNm(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Brand createBrandIfNotFound(String brandName, String intro) {
        Brand brand = brandRepository.findByBrandNm(brandName);

        if(brand == null) {
            brand = Brand.builder()
                    .brandNm(brandName)
                    .intro(intro)
                    .build();
        }
        return brandRepository.save(brand);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category createCategoryIfNotFound(String categoryName, Category parent) {
        Category category = categoryRepository.findByCategoryNm(categoryName);

        if(category == null) {
            category = Category.builder()
                    .categoryNm(categoryName)
                    .parent(parent)
                    .build();
        }
        return categoryRepository.save(category);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Vender createVenderIfNotFound(String vdrName, String vdrSts) {
        Vender vender = venderRepository.findByVdrNm(vdrName);

        if(vender == null) {
            vender = Vender.builder()
                    .vdrNm(vdrName)
                    .vdrSts(vdrSts)
                    .build();
        }
        return venderRepository.save(vender);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product createProductIfNotFound(String productName, LocalDateTime startDate, LocalDateTime endDate, Integer prdSts, Integer prdTp, Integer taxTp
        , String productDesc, Integer prdPrc, Integer salePrc, Integer supplyPrc, Integer totalCnt
            , Brand brand, Category category, Vender vender) {
        Product product = productRepository.findByName(productName);

        if(product == null) {
            product = Product.builder()
                    .name(productName)
                    .productDesc(productDesc)
                    .startDate(startDate)
                    .endDate(endDate)
                    .prdPrc(prdPrc)
                    .salePrc(salePrc)
                    .supplyPrc(supplyPrc)
                    .totalCnt(totalCnt)
                    .prdSts(prdSts)
                    .prdTp(prdTp)
                    .taxTp(taxTp)
                    .brand(brand)
                    .category(category)
                    .vender(vender)
                    .build();
        }
        return productRepository.save(product);
    }
}
