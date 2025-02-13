package com.w2m.naves.spaceship.infraestructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.w2m.naves.spaceship.infraestructure.controller.SpaceshipController.getSpaceshipById(..)) && args(id)")
    public void getSpaceshipById(Long id) {}

    @Around("getSpaceshipById(id)")
    public Object logNegativeId(ProceedingJoinPoint joinPoint, Long id) throws Throwable {
        Optional.ofNullable(id).filter(i -> i < 0).ifPresent(i ->
                log.error("Se ha solicitado una nave con un ID negativo: {} en el método: {}", i, joinPoint.getSignature().toShortString())
        );

        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("Error al ejecutar {}: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
            throw ex;
        }
    }
}

