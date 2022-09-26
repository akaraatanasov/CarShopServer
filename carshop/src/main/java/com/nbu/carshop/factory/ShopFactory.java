package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.api.request.ShopRequest;
import com.nbu.carshop.api.response.ShopResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopFactory {

    public Shop fromShopRequestResource(ShopRequest request) {
        Shop shop = new Shop(request.getName(), Make.valueOf(request.getMake()), RepairType.valueOf(request.getExpertise()), null);
        return shop;
    }

    public ShopResponse fromShop(Shop shop) {
        ShopResponse response = new ShopResponse();
        response.setId(shop.getId());
        response.setMake(shop.getMake().name());
        response.setName(shop.getName());
        response.setExpertise(shop.getExpertise().name());
        return response;
    }

    public List<ShopResponse> fromShopList(List<Shop> shopList) {
        return shopList.stream().map(this::fromShop).collect(Collectors.toList());
    }
}
