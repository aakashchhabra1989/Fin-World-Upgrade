package com.achhabra.finworld.constants;

public enum FrequencyTypeEnum{
	YEARLY(1),	
	HALF_YEARLY(2),
	QUATERLY(4),
	MONTHLY(12),
	BI_MONTHLY(24),
	WEEKLY(52),
	BI_WEEKLY(26);
    
    private double value;
    //Constructor which will initialize the enum
    FrequencyTypeEnum(double id)
    {
    	value = id;
    }
    
    //method to return the direction set by the user which initializing the enum
    public double getFrequencyTypeEnum()
    {
      return value;
    }

}
