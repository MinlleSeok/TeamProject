<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}

#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* Caption of Modal Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation */
.modal-content, #caption {  
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {-webkit-transform:scale(0)} 
  to {-webkit-transform:scale(1)}
}

@keyframes zoom {
  from {transform:scale(0)} 
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}

.divclass {
    width:300px; height:300px; overflow: hidden; background: red; position: relative; margin: 0 auto;
}
.divclass2 {
  margin: 0;
  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}
.imgstyle{width:300px; height:auto;}

.imgstyle2{width:auto; height:300px;}

.imgstyle3{width:300px;  height:300px;}

.divcover {
    width:300px; height:300px; overflow: hidden;
}

.cover{
    display: block;
    /* width: 100%; */
    /* height: 170px; */
    background-size: cover;
    background-position: 50% 50%;
    background-color: #ddd;
    
    margin: 0 auto;

}

.divclass3 {
  margin: 0;
  position: absolute;
  
  left: 50%;
  -ms-transform: translateX(-50%);
  transform: translateX(-50%);
  /*-ms-transform: translateX(-50%);
  transform: translateX(-50%);*/
}
</style>
</head>
<body>

<h2>Image Modal</h2>
<p>In this example, we use CSS to create a modal (dialog box) that is hidden by default.</p>
<p>We use JavaScript to trigger the modal and to display the current image inside the modal when it is clicked on. Also note that we use the value from the image's "alt" attribute as an image caption text inside the modal.</p>
<style>
.divcover {
    width:300px; height:300px; overflow: hidden;
}

.cover{
    display: block;
    /* width: 100%; */
    /* height: 170px; */
    background-size: cover;
    background-position: 50% 50%;
    background-color: #ddd;
    
    margin: 0 auto;

}
</style>

<%String a = "images/api23.jpg";
String b = "images/api30.jpg";%>
    <span class="divcover cover" style="background-image:url('<%=a%>');"> 
        
    </span>
    
    <br>

    <div class="divcover cover" style="background-image:url('<%=b%>');"> 
        
    </div>

    <br>

    <div class="divclass">
        <div id="myImgDiv"><img id="myImg" src="img/building.jpg"></div>
    </div>

    <br>

    <div class="divclass">
        <div id="myImg2Div"><img id="myImg2" src="img/art.jpg"></div>
    
    </div>


<!-- The Modal -->
<div id="myModal" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div>


<script>
// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");




function compareImageSize(imageSource){

    var img00 = document.getElementById(imageSource);
    var imgdiv = document.getElementById(imageSource+"Div");

    if (img00.naturalWidth > img00.naturalHeight) {
        img00.className = "imgstyle2";
        imgdiv.className = "divclass3";
    } else if (img00.naturalWidth < img00.naturalHeight) {
        img00.className = "imgstyle";
        imgdiv.className = "divclass2";
    } else {
        img00.className = "imgstyle3";
        imgdiv.className = "divclass2 divclass3";
    }
}


var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
  captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
  modal.style.display = "none";
}

window.onload = function() {
    compareImageSize("myImg");
    compareImageSize("myImg2");
}
</script>

</body>
</html></html>