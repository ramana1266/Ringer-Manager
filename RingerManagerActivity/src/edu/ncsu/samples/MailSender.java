package edu.ncsu.samples;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MailSender extends Activity {

		public static String email="";
		public static String password="";
        public static void sendEmail(String email1, String currentPlace2) {

                    try {   
                    	
                    if(currentPlace2!="")
                    {
                                   	
                    GMailSender sender = new GMailSender(email, password);
                    sender.sendMail("Sorry I missed your call",   
                            "I am in "+ currentPlace2 + " and so I missed your call. Will call you back whenever I get a chance. Sorry and Thanks",   
                            email1,   
                            email1);
                    Log.i("","2");
                    
                    }
                    
                    else
                    {
                    	GMailSender sender = new GMailSender(email, password);
                        sender.sendMail("Sorry I missed your call",   
                                "I missed your call. Will call you back whenever I get a chance. Sorry and Thanks",   
                                email1,   
                                email1);
                        Log.i("","2");
                    }
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                } 

            }
        }

    
