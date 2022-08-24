package com.revature.pokebowl.memberpayment;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.memberpayment.dto.requests.EditPaymentRequest;
import com.revature.pokebowl.memberpayment.dto.requests.CreatePaymentRequest;
import com.revature.pokebowl.memberpayment.dto.responses.PaymentResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PaymentService {
    //private final Payment sessionPayment = null;
    private final MemberService memberService;
    private final PaymentDao paymentDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public PaymentService(MemberService memberService, PaymentDao paymentDao){
        this.memberService = memberService;
        this.paymentDao = paymentDao;
    }


public PaymentResponse registerPayment(CreatePaymentRequest newPaymentRequest) throws InvalidUserInputException, ResourcePersistanceException{
Payment newPayment = new Payment();
newPayment.setPaymentName(newPaymentRequest.getPaymentName());
newPayment.setProvider(newPaymentRequest.getProvider());
newPayment.setZipCode(newPaymentRequest.getZipCode());
newPayment.setCcv(newPaymentRequest.getCcv());
newPayment.setExpDate(newPaymentRequest.getExpDate());
newPayment.setBalance(newPaymentRequest.getBalance());
newPayment.setPaymentId(UUID.randomUUID().toString());
newPayment.setMember(memberService.getSessionMember());
    logger.info("Payment registration service has begun with the provided: {}", newPayment);
    if (!isPaymentValid(newPayment)) {
        throw new InvalidUserInputException("User input was invalid");
    }
    if(!isPaymentNameAvailable(newPayment.getPaymentName())){
        throw new ResourcePersistanceException("Payment Name provided is already registered please try logging in.");
    }
    paymentDao.create(newPayment);

    return new PaymentResponse(newPayment);
}
    public boolean isPaymentValid(Payment newPayment) {
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if(!notNullOrEmpty.test(newPayment.getPaymentName())){return false;}
        if(!notNullOrEmpty.test(newPayment.getProvider())){return false;}
        if(!notNullOrEmpty.test(newPayment.getZipCode())){return false;}
        if(!notNullOrEmpty.test(newPayment.getCcv())){return false;}
        if(!notNullOrEmpty.test(newPayment.getZipCode())){return false;}
        if(!notNullOrEmpty.test(newPayment.getExpDate().toString())){return false;}
        if(!notNullOrEmpty.test(String.valueOf(newPayment.getBalance()))){return false;}
        return true;
    }
    public List<PaymentResponse> readAll(){

        // Streams are a form of functional programming this is form a declarative programming
        List<PaymentResponse> payments = paymentDao.findAll()
                .stream()//this reads through each value inside of the collection (aka our List)
                //.map(member -> new MemberResponse(member))
                // this is leveraging (::) which is known as the method reference operator, it's taking the method from MemberReponse and applying to all objects in the stream
                .map(PaymentResponse::new)
                .collect(Collectors.toList());
        return payments;
    }
    public PaymentResponse findByPaymentName(String paymentName){
        Payment payment = paymentDao.findByName(paymentName);
        if (payment == null) return null;
        PaymentResponse paymentResponse = new PaymentResponse(payment);
        return paymentResponse;
    }
    public boolean isPaymentNameAvailable(String paymentName){
        return paymentDao.checkPaymentName(paymentName);
    }
    public boolean remove(String paymentName){
        return paymentDao.delete(paymentName);
    }

    public boolean update(EditPaymentRequest editPayment) throws InvalidUserInputException{
        System.out.println("Inside update Member");
        Payment foundPayment = paymentDao.findById(editPayment.getId());
        // Predicate - to evaluate a true or false given a lambda expression
        // Lambda expression (arrow notation) - a syntax for a SINGULAR function
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        // Example of Automatic Dirty Checking
        if(notNullOrEmpty.test(editPayment.getPaymentName())){
            if(!isPaymentNameAvailable(editPayment.getPaymentName())){
                throw new ResourcePersistanceException("The provided payment name is already registered");
            }
            foundPayment.setPaymentName(editPayment.getPaymentName());
        }
        if(notNullOrEmpty.test(String.valueOf(editPayment.getBalance()))){
            foundPayment.setBalance(editPayment.getBalance());
        }
        if(notNullOrEmpty.test(String.valueOf(editPayment.getExpDate()))){
            foundPayment.setExpDate(editPayment.getExpDate());
        }
        if(notNullOrEmpty.test(editPayment.getCcv())){
            foundPayment.setCcv(editPayment.getCcv());
        }
        if(notNullOrEmpty.test(editPayment.getZipCode())){
            foundPayment.setZipCode(editPayment.getZipCode());
        }
        if(notNullOrEmpty.test(editPayment.getProvider())){
            foundPayment.setProvider(editPayment.getProvider());
        }
        return paymentDao.update(foundPayment);
    }



} //end of class