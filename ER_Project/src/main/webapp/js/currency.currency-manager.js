var typeOfCurrency;

$(document).ready(function() {
	$("#buttonToImageCharts").click(function() {
		window.location.href = "/charts/image/"+ $("#typeOfCurrency option:selected").val();
	});
	$("buttonToAnimatedCharts").click(function() {
		this.typeOfCurrency = $("#typeOfCurrency option:selected").val();
		var chart = new CanvasJS.Chart("chartContainer", {
			title: {
				text: $("#typeOfCurrency option:selected").text()
			}, data: {
				
			}
		})
	});
});
