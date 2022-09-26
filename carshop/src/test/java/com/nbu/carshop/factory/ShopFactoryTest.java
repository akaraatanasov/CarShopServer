package com.nbu.carshop.factory;

import com.nbu.carshop.domain.Shop;
import com.nbu.carshop.domain.enums.RepairType;
import com.nbu.carshop.domain.enums.Make;
import com.nbu.carshop.api.request.ShopRequest;
import com.nbu.carshop.api.response.ShopResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShopFactoryTest {

    @InjectMocks
    private ShopFactory shopFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFromShopRequestResourceSuccess() {
        // Given
        ShopRequest request = new ShopRequest();
        request.setExpertise("ENGINE");
        request.setMake("MERCEDES_BENZ");
        request.setName("HoodShop");

        // When
        Shop shop = shopFactory.fromShopRequestResource(request);

        // Then
        assertNotNull(shop);
        assertEquals(Make.MERCEDES_BENZ, shop.getMake());
        assertEquals(RepairType.ENGINE, shop.getExpertise());
        assertEquals("HoodShop", shop.getName());
    }

    @Test
    public void testFromShopSuccess() {
        // Given
        Shop shop = new Shop("HoodShop", Make.MERCEDES_BENZ, RepairType.GENERIC, null);

        // When
        ShopResponse response = shopFactory.fromShop(shop);

        // Then
        assertNotNull(response);
        assertEquals(Make.MERCEDES_BENZ.name(), response.getMake());
        assertEquals(RepairType.GENERIC.name(), response.getExpertise());
        assertEquals("HoodShop", response.getName());
    }

    @Test
    public void testFromShopListSuccess() {
        // Given
        Shop shop = new Shop("HoodShop", Make.MERCEDES_BENZ, RepairType.GENERIC, null);

        // When
        List<ShopResponse> response = shopFactory.fromShopList(Collections.singletonList(shop));

        // Then
        assertNotNull(response);
        assertEquals(Make.MERCEDES_BENZ.name(), response.get(0).getMake());
        assertEquals(RepairType.GENERIC.name(), response.get(0).getExpertise());
        assertEquals("HoodShop", response.get(0).getName());
    }
}
