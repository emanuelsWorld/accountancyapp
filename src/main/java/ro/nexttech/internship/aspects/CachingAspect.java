package ro.nexttech.internship.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ro.nexttech.internship.domain.Invoice;


@Aspect
@Component
@EnableAspectJAutoProxy
public class CachingAspect {
    @Around("execution(* ro.nexttech.internship.repository.InvoiceRepository.findById(..))")
    public void cacheInvoice(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] methodArgs=thisJoinPoint.getArgs();
        Object result=thisJoinPoint.proceed();
        if(result.getClass().isInstance(Invoice.class))
        {
            Invoice invoice=(Invoice)result;
            int id = (Integer) methodArgs[0];
            if (!Invoice.getInvoiceMap().containsKey(id)) {
                Invoice.getInvoiceMap().put(id, invoice);
            }
        }

    }
}
