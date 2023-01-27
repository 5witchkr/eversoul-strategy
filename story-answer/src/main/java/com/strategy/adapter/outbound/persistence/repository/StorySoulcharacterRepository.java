package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorySoulcharacterRepository extends JpaRepository<StorySoulcharacter, Long> {
    StorySoulcharacter getByName(String soul);
}
