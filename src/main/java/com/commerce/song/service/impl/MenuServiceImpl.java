package com.commerce.song.service.impl;

import com.commerce.song.domain.dto.MenuDto;
import com.commerce.song.domain.entity.Menu;
import com.commerce.song.repository.MenuRepository;
import com.commerce.song.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public Page<Menu> getAllMenuPage(PageRequest pageRequest) {
        return menuRepository.findAllToDtoPage(pageRequest);
    }
    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAllToDto();
    }

    @Override
    public Menu save(Menu menu, Long parentId) {
        Menu savedMenu = menuRepository.save(menu);
        if(parentId != null) {
            Optional<Menu> parentMenu = menuRepository.findById(parentId);
            if(!parentMenu.isPresent()) {
                throw new EntityNotFoundException("["+parentId+"] menu is not found");
            }
            parentMenu.ifPresent(savedMenu::setParent);
        }
        return savedMenu;
    }

    @Override
    public void delete(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(!menu.isPresent()) {
            throw new EntityNotFoundException("["+id+"] menu is not found");
        }
        menuRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Menu menu, Long parentId) {
        Optional<Menu> savedMenu = menuRepository.findById(id);
        if(!savedMenu.isPresent()) {
            throw new EntityNotFoundException("["+id+"] menu is not found");
        }

        savedMenu.ifPresent(itemMenu -> itemMenu.updateMenu(menu));
        if(parentId != null) {
            Optional<Menu> parentMenu = menuRepository.findById(parentId);
            if(!parentMenu.isPresent()) {
                throw new EntityNotFoundException("["+id+"] menu's parent is not found");
            }
            savedMenu.ifPresent(itemMenu -> itemMenu.setParent(parentMenu.get()));
        }
    }

    @Override
    public Menu findById(Long id) {
        Optional<Menu> findMenu = menuRepository.findById(id);
        if(!findMenu.isPresent()) {
            throw new EntityNotFoundException("["+id+"] menu is not found");
        }
        return findMenu.get();
    }
}
