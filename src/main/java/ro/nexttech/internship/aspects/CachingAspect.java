package ro.nexttech.internship.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ro.nexttech.internship.domain.Invoice;

import javax.swing.text.html.Option;
import java.util.Optional;


@Aspect
@Component
@EnableAspectJAutoProxy
public class CachingAspect {
    @Around("execution(* ro.nexttech.internship.serviceImpl.InvoiceServiceImpl.findById(..))")
    public Invoice cacheInvoice(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] methodArgs = thisJoinPoint.getArgs();
        Invoice result = (Invoice) thisJoinPoint.proceed();
        int id = (Integer) methodArgs[0];
        if (!Invoice.getInvoiceMap().containsKey(id)) {
            Invoice.getInvoiceMap().put(id, result);

        }
        return result;
    }

}
