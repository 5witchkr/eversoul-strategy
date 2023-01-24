package com.strategy.application.processor;

@FunctionalInterface
public interface CheckBans<DTO, BAN> {

    Boolean check(DTO dto, BAN ban);
}
