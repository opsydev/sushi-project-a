var setup = function(){
	var form = document.getElementById("calculationForm");
	
	form.calculate.onclick = function(e){
		if(validate()){
			//Let's try to get the result
			var xmlhttp;

		    if (window.XMLHttpRequest) {
		    	xmlhttp = new XMLHttpRequest();
		    	
		    	
		    	var p = form.principle.value;
		    	var a;
		    	var i = form.interest.value;
		    	
		    	//Get the amortization value
		    	for(var l in form.amortization){
		    		if(form.amortization[l].checked) a=form.amortization[l].value;	
		    	}
		    	
		    	//Setup the URL
		    	var url = form.action + "?format=json&calculate=calculate&interest="+i+"&principle="+p+"&amortization="+a;
		    	
		    	xmlhttp.onreadystatechange = function() {
		            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		       			var res =document.getElementById("result");
		       			if(xmlhttp.responseText.indexOf("html")!=-1){
		       				res.innerHTML = xmlhttp.responseText;
		       			} else {
		       				res.innerHTML = "<h3 class=\"subheader\">Based on the information provided by you, your monthly payment is: <span class=\"white\">"+xmlhttp.responseText+"</span></h3>";	
		       			}
		                res.style.display = "block";
		            }
		        };

		        xmlhttp.open("GET", url, true);
		        xmlhttp.send();
		    	
		    } else {
		    	// Rely on plain old form submition
		        return;
		    }

		    
		}


		e.preventDefault;
		return false;
	};
};

function validate(){
	var p = document.getElementById("principle").value;
	var i = document.getElementById("interest").value;
	
	var peb = document.getElementById("principle_error");
	var ieb = document.getElementById("interest_error");
	
	var isValid = true;
	
	if(isNaN(p) || p <= 0){
		
		peb.innerHTML = "Principle is invalid";
		peb.style.display="block";
		isValid =  false;
	} else {
		//if no issues, hide error boxes
		peb.style.display = "none";
	}
	
	if(isNaN(i) || i <= 0 || i>=100){
		ieb.innerHTML = "Interest is invalid";
		ieb.style.display="block";
		isValid = false;
	} else {
		//if no issues, hide error boxes
		ieb.style.display = "none";
	}
	return isValid;
	
	
}