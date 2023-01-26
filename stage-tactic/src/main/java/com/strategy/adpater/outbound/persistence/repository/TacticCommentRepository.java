package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.TacticComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacticCommentRepository extends JpaRepository<TacticComment, Long> {
}
