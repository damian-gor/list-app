package com.damgor.listapp.repositories;

import com.damgor.listapp.models.ProductItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductItemImageRepository extends JpaRepository<ProductItemImage, Long> {
    Optional<ProductItemImage> findByProductItemId(Long productItemId);

    @Query("SELECT i.productItemId from ProductItemImage i where i.productItemId in :productsOnListIds")
    List<Long> getIdsOfProductItemsWithImages(List<Long> productsOnListIds);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductItemImage i where i.productItemId = :productItemId")
    void deleteByProductItemId(Long productItemId);
}
