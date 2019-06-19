
package com.Finale;

import java.util.Random;

public class VerificationCodeGenerator {
    
    private Random random = new Random();
    private int verificationCodeInt = 0;
    private String verificationCode;
    private int min = 0;
    private int max = 0;
    
    public VerificationCodeGenerator(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    
    public void GenerateVerificationCode()
    {
        this.verificationCodeInt = (random.nextInt((max - min) + 1) + min);
        this.verificationCode = String.valueOf(this.verificationCodeInt);
    }
    
    public String GetVerificationCode()
    {
        return verificationCode;
    }
    
    
    
}
