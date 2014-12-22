package org.trialVersionv1.main;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.trialVersionv1.misc.HashFunction;
import org.trialVersionv1.misc.MailSender;

@WebService
public class GenerateSerialKey
{
  public String serialKey;
  private String oneMonth = "1 month";
  private String sixMonth = "6 months";
  private String oneYear = "1 Year";
  private float oneMonthPay = 10.0F;
  private float sixMonthPay = 50.0F;
  private float oneYearPay = 100.0F;
  HashFunction hashFunction = new HashFunction();
  
  public Boolean generateKey(String customerDetails)
  {
	String email=stringSplitter(customerDetails);
	Boolean result=false;
    try
    {
      this.serialKey = this.hashFunction.generateSerialKey(customerDetails);
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
//    return this.serialKey;
    
    /**
     * Sending email to the customer according to the provided details
     */
    MailSender mailSender = new MailSender();
//    mailSender.sendMail("pranish.stha@gmail.com", "Registration Key", "This is your registration key. Thank you for being our member. Serial Key");
    System.out.println(serialKey);
	Boolean b = mailSender.sendMail(email, "Registration Key", "This is your registration key. Thank you for being our member : " + serialKey);
	if(b){
//		System.out.println("Sent");
		result=true;
	}else {
//		System.out.println("Unsent");
		result=false;
	}
	return result;
  }
  
  private String stringSplitter(String customerDetails) {
	// TODO Auto-generated method stub
	  String[] parts=customerDetails.split(":");
	  String email =parts[3];
	return email;
}

public String encryptExpDate(Date registrationDate, int duration, float amount)
  {
    HashFunction hashFunction = new HashFunction();
    

    Calendar calendarExpDate = Calendar.getInstance();
    

    calendarExpDate.setTime(registrationDate);
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    if ((duration == 1) && (amount == this.oneMonthPay)) {
      calendarExpDate.add(2, 1);
    } else if ((duration == 6) && (amount == this.sixMonthPay)) {
      calendarExpDate.add(2, 6);
    } else if (duration == this.oneYearPay) {
      calendarExpDate.add(2, 12);
    }
    String encNum = hashFunction.encryptedDate(calendarExpDate);
    
    return encNum;
  }
  
  public List<String> subscriptionRate()
  {
    List<String> rateList = new ArrayList();
    
    rateList.add(this.oneMonth + ":" + this.oneMonthPay);
    rateList.add(this.sixMonth + ":" + this.sixMonthPay);
    rateList.add(this.oneYear + ":" + this.oneYearPay);
    
    return rateList;
  }
/*  public static void main(String[] args) {
	  GenerateSerialKey gsk = new GenerateSerialKey();
	  gsk.generateKey("Therewarawer");
}*/
}
