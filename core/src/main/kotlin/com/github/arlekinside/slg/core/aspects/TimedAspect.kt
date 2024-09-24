package com.github.arlekinside.slg.core.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.time.measureTime

@Aspect
@Component
class TimedAspect {

    private val logger: Logger = LoggerFactory.getLogger(TimedAspect::class.java)

    @Around("@annotation(Timed)")
    fun timed(joinPoint: ProceedingJoinPoint): Any {
        var result: Any
        val time = measureTime {
            result = joinPoint.proceed()
        }
        logger.info("[${joinPoint.target.javaClass.simpleName}#${joinPoint.signature.name}] Execution time: ${time.inWholeMilliseconds}ms")
        return result
    }

}