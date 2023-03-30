package com.zengshen.service.impl;

import com.zengshen.mapper.CarouselMapper;
import com.zengshen.model.pojo.Carousel;
import com.zengshen.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getCarouselList() {
        List<Carousel> carousels = carouselMapper.selectAll();
        return carousels;
    }
}
