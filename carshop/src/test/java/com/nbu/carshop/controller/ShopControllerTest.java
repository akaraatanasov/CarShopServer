package com.nbu.carshop.controller;

import com.nbu.carshop.api.request.ShopRequest;
import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.factory.ShopFactory;
import com.nbu.carshop.api.response.ShopResponse;
import com.nbu.carshop.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

public class ShopControllerTest {

    @InjectMocks
    private ShopController shopController;

    @Mock
    private ShopService shopService;

    @Mock
    private ShopFactory shopFactory;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllShopsSuccess() {
        // Given
        Shop shop = new Shop("TestShop", Make.RENAULT, RepairType.GENERIC, 1L);
        doReturn(Collections.singletonList(shop)).when(shopService).getAllShops();
        doCallRealMethod().when(shopFactory).fromShopList(anyList());

        // When
        ResponseEntity<List<ShopResponse>> response = shopController.getAllShops();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testAddShopSuccess() {
        // Given
        ShopRequest request = new ShopRequest();
        request.setName("TestShop");
        request.setMake("RENAULT");
        request.setExpertise("GENERIC");

        doCallRealMethod().when(shopFactory).fromShopRequestResource(any(ShopRequest.class));

        // When
        ResponseEntity<ShopResponse> response = shopController.createShop(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
