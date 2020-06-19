function upDate(previewPic) {

    console.log(previewPic);
    $('#image').css('background-image', 'url(' + previewPic.src + ')');            //plain image obtained after we finish hovering
    $('#image').html(previewPic.alt);                                              //image after hovering to another image

}
function unDo() {
    $('#image').css('background-image','none');                                      //image after moving the cursor
    $('#image').html("move the cursor to hover");                                   //text after moving the cursor
}