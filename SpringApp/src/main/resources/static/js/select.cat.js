 window.onload = function() {
	 
	 var eSelect = document.getElementById('primary');
	 var strUser = eSelect.options[eSelect.selectedIndex].value;
	 
	 
	 eSelect.onchange = function() {
		 strUser = eSelect.options[eSelect.selectedIndex].value;
		 if (strUser == "AUTO"){
		document.getElementById('myAnchor').setAttribute("href", "https://www.w3schools.com"); 
		
		document.getElementById('secondary').setAttribute("th:each", "primaryCat : ${primaryCat}"); 
		document.getElementById('secondary').setAttribute("th:text", "${primaryCat.getName()}"); 
		document.getElementById('secondary').setAttribute("th:value", "${primaryCat"); 
		
		 }
		 	
	 }
}