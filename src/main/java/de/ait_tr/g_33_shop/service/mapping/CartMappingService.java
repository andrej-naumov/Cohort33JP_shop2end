package de.ait_tr.g_33_shop.service.mapping;

import de.ait_tr.g_33_shop.domain.dto.CartDto;
import de.ait_tr.g_33_shop.domain.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMappingService.class)
public interface CartMappingService {

    @Mapping(target = "id", ignore = true)
    Cart mapDtoToEntity(CartDto dto);

    CartDto mapEntityToDto(Cart entity);
}