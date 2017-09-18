function loadPageInMainDiv(pageName){
	$('#maincontainer').html('');
	$('#maincontainer').load(pageName);
}
function validateAmount(s) {
    var rgx = /^[0-9]*\.?[0-9]*$/;
    return s.match(rgx);
}
function validateYear(obj) {
	
} 
function numericValidation(txtvalue) {
    var e = event || evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
    if (!(document.getElementById(txtvalue.id).value))
     {
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }
    else {
    	var val = document.getElementById(txtvalue.id).value;
        if(charCode==46 || (charCode > 31 && (charCode > 47 && charCode < 58)) )
         {
            var points = 0;            
            points = val.indexOf(".", points);                    
            if (points >= 1 && charCode == 46)
            {      
               return false;
            }                 
            if (points == 1) 
            {
                var lastdigits = val.substring(val.indexOf(".") + 1, val.length);
                
                if (lastdigits.length >= 2)
                {
                	alertMessage("Two decimal places only allowed");
                    return false;
                }
            }
            return true;
        }
        else {
        	alertMessage("Only Numarics allowed");
            return false;
        }
    }
}
function alertMessage(msg){
	alert(msg);
}

function isInvalidTextBox(obj){
	if(obj.value!='undefined' && obj.value!=''){
		return false;
    }else{
    	return true;
    }	
}

function isInvalidDropdown(objValue){
	if(objValue!='undefined' && objValue!='' && objValue!='-1'){
		return false;
    }else{
    	return true;
    }
}

function isInvalidCheckbox(obj){
	if(obj.value!='undefined' && obj.value!='')
	{
		return false;
    }else{
    	return true;
    }
}

function isInvalidRadioBtn(obj){
}

function isValidYear(objValue){
	
}