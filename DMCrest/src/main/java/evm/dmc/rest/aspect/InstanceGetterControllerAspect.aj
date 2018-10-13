package evm.dmc.rest.aspect;

import evm.dmc.rest.annotation.HateoasRelation;
import evm.dmc.webApi.dto.AbstractDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Defines aspect around controllers that implement instance getter
 * controller interface
 */
@Aspect
@Component
@Slf4j
public class InstanceGetterControllerAspect {

    /**
     * Adds HATEOAS relation links to returned DTO
     * @param pjp Spring aspect join point
     * @return DTO with HATEOAS Relation links
     * @throws Throwable when error occurs
     */
    @Around("execution(* evm.dmc.rest.controllers.interfaces.InstanceGetterController.getInstance(..))")
    public AbstractDto addLinksToInstanceAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> clazz = pjp.getSignature().getDeclaringType();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method calledMethod = methodSignature.getMethod();

        AbstractDto dto = (AbstractDto) pjp.proceed();
        dto = addSelfLinkToDto(dto, clazz, calledMethod);
        dto = addRelationLinksToDto(dto, clazz);

        return dto;
    }

    /**
     * Adds HATEOAS relation links to returned DTO list
     * @param pjp Spring aspect join point
     * @return list of DTOs with HATEOAS Relation links
     * @throws Throwable when error occurs
     */
    @Around("execution(* evm.dmc.rest.controllers.interfaces.InstanceGetterController.getInstancesList(..))")
    public List<AbstractDto> addLinksToInstancesListAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> clazz = pjp.getSignature().getDeclaringType();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method calledMethod = methodSignature.getMethod();

        List dtoList = (List) pjp.proceed();
        List<AbstractDto> proceedDtoList = new ArrayList<>(dtoList.size());
        for(Object dto : dtoList) {
            AbstractDto proceedDto = addSelfLinkToDto((AbstractDto) dto, clazz, calledMethod);
            proceedDto = addRelationLinksToDto(proceedDto, clazz);
            proceedDtoList.add(proceedDto);
        }

        return proceedDtoList;
    }

    /**
     * adds HATEOAS self link for DTO
     * @param dto DTO
     * @return DTO with self link
     */
    private AbstractDto addSelfLinkToDto(AbstractDto dto, Class<?> clazz, Method method) {
        Link selfLink = linkTo(clazz, method, dto.getAccountId(), dto.getDtoId())
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    /**
     * adds HATEOAS relation links for DTO
     * @param dto DTO
     * @return DTO with self link
     */
    private AbstractDto addRelationLinksToDto(AbstractDto dto, Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(HateoasRelation.class)) {
                HateoasRelation annotation = method.getAnnotation(HateoasRelation.class);
                String relationName = annotation.value();
                Link link = linkTo(clazz, method, dto.getAccountId(), dto.getDtoId())
                        .withRel(relationName);
                dto.add(link);
            }
        }
        return dto;
    }
}
